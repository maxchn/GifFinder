package com.giffinder.app.domain.usecase.gif

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.giffinder.app.core.data.remote.NetworkManager
import com.giffinder.app.core.domain.BaseUseCase
import com.giffinder.app.data.remote.dto.request.GifParams
import com.giffinder.app.data.repository.gif.GifRepository
import com.giffinder.app.domain.common.Constants.PAGE_SIZE
import com.giffinder.app.domain.entity.GifData

class GetGifListUseCase(
    private val repository: GifRepository,
    private val networkManager: NetworkManager
) : BaseUseCase() {

    operator fun invoke(params: GifParams): Pager<Int, GifData> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GifListPagingSource(
                    imageRepository = repository,
                    imageParams = params,
                    networkManager = networkManager
                )
            }
        )
    }
}
