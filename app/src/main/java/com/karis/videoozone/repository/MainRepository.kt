package com.karis.videoozone.repository

import com.karis.videoozone.data.network.ApiService
import com.karis.videoozone.models.YtResponse
import retrofit2.Response
import javax.inject.Inject


class MainRepository @Inject constructor(
    private val apiService: ApiService,
) {
    suspend fun fetchVideos(): Response<YtResponse>{
        return apiService.fetchMostPopularVideos()
    }
}