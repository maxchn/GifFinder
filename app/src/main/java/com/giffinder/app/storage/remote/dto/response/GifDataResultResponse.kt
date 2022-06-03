package com.giffinder.app.storage.remote.dto.response

import com.giffinder.app.domain.common.Constants
import com.google.gson.annotations.SerializedName

data class GifDataResultResponse(
    @SerializedName("data")
    val data: List<GifDataResponse>?,
    @SerializedName("meta")
    val meta: MetaResponse?,
    @SerializedName("pagination")
    val pagination: PaginationResponse?
)

fun GifDataResultResponse?.isSuccess(): Boolean {
    return this != null && meta?.status == Constants.STATUS_OK
}
