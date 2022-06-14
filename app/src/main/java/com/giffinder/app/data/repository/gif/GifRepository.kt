package com.giffinder.app.data.repository.gif

import androidx.paging.Pager
import com.giffinder.app.data.local.dto.GifLocal
import com.giffinder.app.domain.entity.GifParams

interface GifRepository {
    fun loadGifList(params: GifParams): Pager<Int, GifLocal>
    suspend fun updateGif(updatedGif: GifLocal)
    suspend fun blockGif(gif: GifLocal)
}
