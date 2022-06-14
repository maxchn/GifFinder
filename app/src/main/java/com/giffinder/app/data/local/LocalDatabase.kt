package com.giffinder.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.giffinder.app.data.local.dao.BlockImageDao
import com.giffinder.app.data.local.dao.GifDao
import com.giffinder.app.data.local.dto.BlockGifLocal
import com.giffinder.app.data.local.dto.GifLocal

@Database(entities = [GifLocal::class, BlockGifLocal::class], version = 2)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun gifDao(): GifDao
    abstract fun blockImageDao(): BlockImageDao

    companion object {
        const val NAME = "GifDatabase"
    }
}
