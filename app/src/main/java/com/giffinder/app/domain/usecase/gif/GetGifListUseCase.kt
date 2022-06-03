package com.giffinder.app.domain.usecase.gif

import com.giffinder.app.core.domain.BaseUseCase
import com.giffinder.app.core.presentation.Resource
import com.giffinder.app.domain.common.mapper.toGifDataResult
import com.giffinder.app.domain.entity.GifDataResult
import com.giffinder.app.storage.remote.dto.request.GifParams
import com.giffinder.app.storage.remote.repository.gif.GifRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetGifListUseCase(private val repository: GifRepository) : BaseUseCase() {

    suspend operator fun invoke(params: GifParams): Resource<GifDataResult> {
        return withContext(Dispatchers.IO) {
            executeRemote(
                call = { repository.loadGifList(params) },
                mapper = { it.toGifDataResult() }
            )
        }
    }
}
