package com.giffinder.app.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class DownsizedResponse(
    @SerializedName("url")
    val url: String?
)
