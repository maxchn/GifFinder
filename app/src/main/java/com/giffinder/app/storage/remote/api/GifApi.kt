package com.giffinder.app.storage.remote.api

import com.giffinder.app.storage.remote.dto.response.GifDataResultResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GifApi {
    @GET("v1/gifs/search")
    suspend fun loadImages(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("rating") rating: String,
        @Query("lang") lang: String
    ): GifDataResultResponse
}
