package com.giffinder.app.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GifLocal(
    @PrimaryKey
    val id: String,
    val name: String,
    val remoteUrl: String,
    val localUrl: String = "",
    val query: String
)
