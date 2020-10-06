package com.karis.videoozone.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.karis.videoozone.models.Videoitem

@Dao
interface VideosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllVideos(trendingVideos: List<Videoitem>)

    @Query("SELECT * FROM videos_table")
    fun getAllVideos(): LiveData<List<Videoitem>>
}