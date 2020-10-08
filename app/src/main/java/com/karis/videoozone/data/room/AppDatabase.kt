package com.karis.videoozone.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.karis.videoozone.model.Videoitem

@Database(entities = [Videoitem::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun videosDao(): VideosDao

}