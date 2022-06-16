package com.giffinder.app.data.repository.gif

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.giffinder.app.core.data.remote.NetworkManager
import com.giffinder.app.data.local.LocalDatabase
import com.giffinder.app.data.local.dto.GifLocal
import com.giffinder.app.data.remote.api.GifApi
import com.giffinder.app.data.remote.dto.response.isSuccess
import com.giffinder.app.domain.common.Constants
import com.giffinder.app.domain.common.Constants.DEFAULT_OFFSET
import com.giffinder.app.domain.common.mapper.toLocalGifList
import com.giffinder.app.domain.common.mapper.toPagination
import com.giffinder.app.domain.entity.GifParams
import com.giffinder.app.domain.entity.Pagination
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class GifRemoteMediator(
    private val networkManager: NetworkManager,
    private val params: GifParams,
    private val db: LocalDatabase,
    private val imageApi: GifApi
) : RemoteMediator<Int, GifLocal>() {

    private val userDao = db.gifDao()

    private var lastPagination: Pagination? = null

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, GifLocal>
    ): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> DEFAULT_OFFSET
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> lastPagination?.let { it.offset + it.count } ?: 0
            }

            networkManager.startProcessing()

            val response = imageApi.loadImages(
                apiKey = params.apiKey,
                query = params.query,
                limit = Constants.PAGE_SIZE,
                offset = loadKey,
                rating = params.rating,
                lang = params.lang
            )

            if (response.isSuccess()) {
                lastPagination = response.pagination?.toPagination()

                db.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        userDao.deleteByQuery(params.query)
                    }

                    val receivedGifList = response.data.orEmpty()
                    val allBlockGif = db.blockImageDao().getAll()

                    userDao.insertAll(receivedGifList.filter {
                        !allBlockGif.contains(it.id)
                    }.toLocalGifList(params.query))
                }

                MediatorResult.Success(
                    endOfPaginationReached =
                    response.pagination!!.offset!! + response.pagination.count!!
                            >= response.pagination.totalCount!!
                )
            } else {
                MediatorResult.Error(Error())
            }
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: HttpException) {
            MediatorResult.Error(e)
        } finally {
            networkManager.stopProcessing()
        }
    }
}
