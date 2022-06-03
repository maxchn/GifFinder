package com.giffinder.app.storage.remote.repository.gif

import com.giffinder.app.storage.remote.api.GifApi
import com.giffinder.app.storage.remote.dto.request.GifParams
import com.giffinder.app.storage.remote.dto.response.GifDataResultResponse

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
