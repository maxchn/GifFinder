package com.giffinder.app.domain.common.mapper

import com.giffinder.app.data.local.dto.GifLocal
import com.giffinder.app.data.remote.dto.response.GifDataResponse
import com.giffinder.app.data.remote.dto.response.PaginationResponse
import com.giffinder.app.domain.common.orDefault
import com.giffinder.app.domain.entity.GifData
import com.giffinder.app.domain.entity.Pagination

fun PaginationResponse.toPagination(): Pagination {
    return Pagination(
        count = count.orDefault(),
        offset = offset.orDefault(),
        totalCount = totalCount.orDefault()
    )
}

fun GifData.toLocalGif(): GifLocal {
    return GifLocal(
        id = id,
        name = name,
        remoteUrl = remoteUrl,
        localUrl = localUrl,
        query = query
    )
}

fun GifLocal.toGif(): GifData {
    return GifData(
        id = id,
        name = name,
        remoteUrl = remoteUrl,
        localUrl = localUrl,
        query = query
    )
}

fun GifDataResponse.toLocalGif(query: String): GifLocal {
    return GifLocal(
        id = id.orEmpty(),
        name = title.orEmpty(),
        remoteUrl = images?.downsized?.url.orEmpty(),
        query = query
    )
}

fun List<GifDataResponse>.toLocalGifList(query: String): List<GifLocal> {
    return this.map { it.toLocalGif(query) }
}
