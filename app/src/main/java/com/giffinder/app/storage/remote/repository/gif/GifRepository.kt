package com.giffinder.app.storage.remote.repository.gif

import com.giffinder.app.storage.remote.dto.request.GifParams
import com.giffinder.app.storage.remote.dto.response.GifDataResultResponse

interface GifRepository {
    suspend fun loadGifList(params: GifParams): GifDataResultResponse
}
