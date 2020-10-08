package com.karis.videoozone.di

import android.app.Application
import androidx.room.Room
import com.karis.videoozone.data.room.AppDatabase
import com.karis.videoozone.data.room.VideosDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@InstallIn(ApplicationComponent::class)

@Module
object DatabaseModule {

    @Provides
    fun providesAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "videos.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesHoursLeaderDao(appDatabase: AppDatabase): VideosDao {
        return appDatabase.videosDao()
    }


}