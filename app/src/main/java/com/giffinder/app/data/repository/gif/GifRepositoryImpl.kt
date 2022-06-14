package com.giffinder.app.data.repository.gif

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.withTransaction
import com.giffinder.app.data.local.LocalDatabase
import com.giffinder.app.data.local.dto.BlockGifLocal
import com.giffinder.app.data.local.dto.GifLocal
import com.giffinder.app.data.remote.api.GifApi
import com.giffinder.app.domain.common.Constants.PAGE_SIZE
import com.giffinder.app.domain.entity.GifParams

class GifRepositoryImpl(
    private val api: GifApi,
    private val db: LocalDatabase
) : GifRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun loadGifList(params: GifParams): Pager<Int, GifLocal> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE
            ),
            remoteMediator = GifRemoteMediator(params, db, api)
        ) {
            db.gifDao().pagingSource(params.query)
        }
    }

    override suspend fun updateGif(updatedGif: GifLocal) {
        db.gifDao().update(updatedGif)
    }

    override suspend fun blockGif(gif: GifLocal) {
        db.withTransaction {
            db.gifDao().deleteById(gif.id)
            db.blockImageDao().insert(BlockGifLocal(sign = gif.id))
        }
    }
}
