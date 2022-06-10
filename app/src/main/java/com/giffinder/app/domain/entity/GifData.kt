package com.giffinder.app.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GifData(
    val id: String,
    val name: String,
    val remoteUrl: String,
    val localUrl: String,
    val query: String
) : Parcelable
