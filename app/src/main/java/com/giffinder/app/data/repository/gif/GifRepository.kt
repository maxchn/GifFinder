package com.giffinder.app.data.repository.gif

import com.giffinder.app.data.remote.dto.request.GifParams
import com.giffinder.app.data.remote.dto.response.GifDataResultResponse

interface GifRepository {
    suspend fun loadGifList(params: GifParams): GifDataResultResponse
}
