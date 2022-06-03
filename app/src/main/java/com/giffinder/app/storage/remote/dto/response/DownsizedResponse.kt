package com.giffinder.app.storage.remote.dto.response

import com.google.gson.annotations.SerializedName

data class DownsizedResponse(
    @SerializedName("url")
    val url: String?
)
