package com.giffinder.app.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.giffinder.app.data.local.dto.BlockGifLocal

@Dao
interface BlockImageDao {
    @Insert
    suspend fun insert(blockImage: BlockGifLocal)

    @Query("SELECT sign FROM BlockGifLocal")
    suspend fun getAll(): List<String>
}
