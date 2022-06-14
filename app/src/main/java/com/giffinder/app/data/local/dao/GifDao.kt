package com.giffinder.app.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.giffinder.app.data.local.dto.GifLocal

@Dao
interface GifDao {
    @Query("SELECT * FROM GifLocal WHERE query = :query")
    fun pagingSource(query: String): PagingSource<Int, GifLocal>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(images: List<GifLocal>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(image: GifLocal)

    @Query("DELETE FROM GifLocal WHERE query = :query")
    suspend fun deleteByQuery(query: String)

    @Query("DELETE FROM GifLocal WHERE id = :id")
    suspend fun deleteById(id: String)
}
