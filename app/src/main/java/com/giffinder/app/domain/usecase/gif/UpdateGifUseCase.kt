package com.giffinder.app.domain.usecase.gif

import com.giffinder.app.core.domain.BaseUseCase
import com.giffinder.app.data.repository.gif.GifRepository
import com.giffinder.app.domain.common.mapper.toLocalGif
import com.giffinder.app.domain.entity.GifData

class UpdateGifUseCase(private val repository: GifRepository) : BaseUseCase() {

    suspend operator fun invoke(updatedItem: GifData) {
        repository.updateGif(updatedItem.toLocalGif())
    }
}
