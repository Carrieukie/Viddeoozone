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

    @Query("DELETE FROM videos_table WHERE id = :id")
    fun deleteVideo(id: String): LiveData<List<Videoitem>>
}