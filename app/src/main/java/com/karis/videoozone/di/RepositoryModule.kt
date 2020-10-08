package com.karis.videoozone.di

import com.karis.videoozone.data.retrofit.ApiService
import com.karis.videoozone.data.room.AppDatabase
import com.karis.videoozone.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule @Inject constructor(){

    @Provides
    fun provideMainrepository(apiService: ApiService, appDatabase : AppDatabase): MainRepository{
        return MainRepository(apiService , appDatabase)
    }

}