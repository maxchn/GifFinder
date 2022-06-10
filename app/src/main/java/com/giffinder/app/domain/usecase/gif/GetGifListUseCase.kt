package com.giffinder.app.domain.usecase.gif

import androidx.paging.Pager
import com.giffinder.app.core.domain.BaseUseCase
import com.giffinder.app.data.local.dto.GifLocal
import com.giffinder.app.data.repository.gif.GifRepository
import com.giffinder.app.domain.entity.GifParams

class GetGifListUseCase(private val repository: GifRepository) : BaseUseCase() {

    operator fun invoke(params: GifParams): Pager<Int, GifLocal> {
        return repository.loadGifList(params)
    }
}
