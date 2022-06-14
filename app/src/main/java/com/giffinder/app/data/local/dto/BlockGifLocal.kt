package com.giffinder.app.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BlockGifLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val sign: String
)
