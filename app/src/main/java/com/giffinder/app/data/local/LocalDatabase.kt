package com.giffinder.app.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.giffinder.app.data.local.dao.GifDao
import com.giffinder.app.data.local.dto.GifLocal

@Database(entities = [GifLocal::class], version = 1)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun gifDao(): GifDao

    companion object {
        const val NAME = "GifDatabase"
    }
}
