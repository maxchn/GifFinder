package com.giffinder.app.storage.remote.dto.request

data class GifParams(
    val apiKey: String,
    val query: String,
    val limit: Int,
    val offset: Int,
    val rating: String,
    val lang: String
)