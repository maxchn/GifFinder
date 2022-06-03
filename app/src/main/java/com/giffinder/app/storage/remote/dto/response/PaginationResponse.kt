package com.giffinder.app.storage.remote.dto.response

import com.google.gson.annotations.SerializedName

data class PaginationResponse(
    @SerializedName("count")
    val count: Int?,
    @SerializedName("offset")
    val offset: Int?,
    @SerializedName("total_count")
    val totalCount: Int?
)
