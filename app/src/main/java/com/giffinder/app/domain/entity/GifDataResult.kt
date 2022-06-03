package com.giffinder.app.domain.entity

import com.giffinder.app.domain.common.Constants

data class GifDataResult(
    val data: List<GifData>?,
    val meta: Meta?,
    val pagination: Pagination?
)

fun GifDataResult?.isSuccess(): Boolean {
    return this != null && meta?.status == Constants.STATUS_OK
}
