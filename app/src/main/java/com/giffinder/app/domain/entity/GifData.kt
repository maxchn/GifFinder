package com.giffinder.app.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GifData(
    val id: String,
    val images: Images?,
    val title: String,
    val url: String
) : Parcelable
