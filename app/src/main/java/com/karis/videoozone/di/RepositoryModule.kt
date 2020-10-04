package com.karis.videoozone.di

import com.karis.videoozone.data.network.ApiService
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
    fun provideMainrepository(apiService: ApiService): MainRepository{
        return MainRepository(apiService )
    }

}