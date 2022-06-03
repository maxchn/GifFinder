package com.giffinder.app.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class ImagesResponse(
    @SerializedName("downsized")
    val downsized: DownsizedResponse?
)
