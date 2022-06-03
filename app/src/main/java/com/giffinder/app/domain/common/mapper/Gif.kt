package com.giffinder.app.domain.common.mapper

import com.giffinder.app.data.remote.dto.response.DownsizedResponse
import com.giffinder.app.data.remote.dto.response.GifDataResponse
import com.giffinder.app.data.remote.dto.response.GifDataResultResponse
import com.giffinder.app.data.remote.dto.response.ImagesResponse
import com.giffinder.app.data.remote.dto.response.MetaResponse
import com.giffinder.app.data.remote.dto.response.PaginationResponse
import com.giffinder.app.domain.common.orDefault
import com.giffinder.app.domain.entity.Downsized
import com.giffinder.app.domain.entity.GifData
import com.giffinder.app.domain.entity.GifDataResult
import com.giffinder.app.domain.entity.Images
import com.giffinder.app.domain.entity.Meta
import com.giffinder.app.domain.entity.Pagination

fun DownsizedResponse.toDownsized(): Downsized {
    return Downsized(url.orEmpty())
}

fun ImagesResponse.toImages(): Images {
    return Images(downsized?.toDownsized())
}

fun GifDataResponse.toImageData(): GifData {
    return GifData(
        id = id.orEmpty(),
        images = images?.toImages(),
        title = title.orEmpty(),
        url = url.orEmpty()
    )
}

fun MetaResponse.toMeta(): Meta {
    return Meta(
        message = message.orEmpty(),
        responseId = responseId.orEmpty(),
        status = status.orDefault()
    )
}

fun PaginationResponse.toPagination(): Pagination {
    return Pagination(
        count = count.orDefault(),
        offset = offset.orDefault(),
        totalCount = totalCount.orDefault()
    )
}

fun GifDataResultResponse.toGifDataResult(): GifDataResult {
    return GifDataResult(
        data = data?.map { it.toImageData() },
        meta = meta?.toMeta(),
        pagination = pagination?.toPagination()
    )
}
