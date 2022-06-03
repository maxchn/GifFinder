package com.giffinder.app.storage.remote.dto.response

import com.google.gson.annotations.SerializedName

data class GifDataResponse(
    @SerializedName("id")
    val id: String?,
    @SerializedName("images")
    val images: ImagesResponse?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?
)
