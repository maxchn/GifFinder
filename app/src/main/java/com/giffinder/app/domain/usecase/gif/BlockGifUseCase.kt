package com.giffinder.app.domain.usecase.gif

import com.giffinder.app.data.repository.gif.GifRepository
import com.giffinder.app.domain.common.mapper.toLocalGif
import com.giffinder.app.domain.entity.GifData

class BlockGifUseCase(private val repository: GifRepository) {

    suspend operator fun invoke(image: GifData) {
        repository.blockGif(image.toLocalGif())
    }
}
