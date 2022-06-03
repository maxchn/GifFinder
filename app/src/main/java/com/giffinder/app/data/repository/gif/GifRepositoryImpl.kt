package com.giffinder.app.data.repository.gif

import com.giffinder.app.data.remote.api.GifApi
import com.giffinder.app.data.remote.dto.request.GifParams
import com.giffinder.app.data.remote.dto.response.GifDataResultResponse

class GifRepositoryImpl(private val api: GifApi) : GifRepository {

    override suspend fun loadGifList(params: GifParams): GifDataResultResponse {
        return api.loadImages(
            apiKey = params.apiKey,
            query = params.query,
            limit = params.limit,
            offset = params.offset,
            rating = params.rating,
            lang = params.lang
        )
    }
}
