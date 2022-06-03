package com.giffinder.app.data.remote.dto.response

import com.google.gson.annotations.SerializedName

data class MetaResponse(
    @SerializedName("msg")
    val message: String?,
    @SerializedName("response_id")
    val responseId: String?,
    @SerializedName("status")
    val status: Int?
)
