package com.karis.videoozone.data.network

import com.karis.videoozone.models.YtResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService{
    @GET
    suspend fun fetchVideos(): Response<List<YtResponse>>

}