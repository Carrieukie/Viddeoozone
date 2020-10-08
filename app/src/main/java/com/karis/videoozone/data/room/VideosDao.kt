package com.karis.videoozone.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karis.videoozone.model.Videoitem

@Dao
interface VideosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllVideos(trendingVideos: List<Videoitem>)

    @Query("SELECT * FROM videos_table")
    fun getAllVideos(): LiveData<List<Videoitem>>
}