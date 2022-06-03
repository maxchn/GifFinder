package com.giffinder.app.domain.usecase.gif

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.giffinder.app.core.data.remote.NetworkManager
import com.giffinder.app.data.remote.dto.request.GifParams
import com.giffinder.app.data.repository.gif.GifRepository
import com.giffinder.app.domain.common.Constants.DEFAULT_OFFSET
import com.giffinder.app.domain.common.Constants.PAGE_SIZE
import com.giffinder.app.domain.common.mapper.toGifDataResult
import com.giffinder.app.domain.entity.GifData
import com.giffinder.app.domain.entity.isSuccess

class GifListPagingSource(
    private val imageRepository: GifRepository,
    private val imageParams: GifParams,
    private val networkManager: NetworkManager
) : PagingSource<Int, GifData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, GifData> {
        val offset = params.key ?: DEFAULT_OFFSET

        return try {
            networkManager.startProcessing()

            val result = imageRepository.loadGifList(
                imageParams.copy(
                    offset = offset,
                    limit = PAGE_SIZE
                )
            ).toGifDataResult()

            val isSuccess = result.isSuccess()

            if (isSuccess) {
                val nextKey = result.pagination?.let { pagination ->
                    if (pagination.offset + pagination.count < pagination.totalCount) {
                        pagination.offset + pagination.count
                    } else {
                        null
                    }
                }

                LoadResult.Page(
                    data = result.data ?: emptyList(),
                    prevKey = null, // Only paging forward.
                    // assume that if a full page is not loaded, that means the end of the data
                    nextKey = nextKey
                )
            } else {
                LoadResult.Error(Exception())
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        } finally {
            networkManager.stopProcessing()
        }
    }

    override fun getRefreshKey(state: PagingState<Int, GifData>): Int? {
        // We need to get the previous key (or next key if previous is null) of the page
        // that was closest to the most recently accessed index.
        // Anchor position is the most recently accessed index
        return null
    }
}
