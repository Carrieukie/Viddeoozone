package com.karis.videoozone.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.karis.videoozone.models.Videoitem

@Database(entities = [Videoitem::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun videosDao(): VideosDao

}