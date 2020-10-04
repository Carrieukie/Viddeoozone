package com.karis.videoozone.data.network

import com.karis.videoozone.models.YtResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService{
    @GET("videos?part=snippet,statistics&key=AIzaSyCCR1FUBgD65VTZ3Ga0QBe8_bOvxlyIa9o&maxResults=200&chart=mostPopular")
    suspend fun fetchMostPopularVideos(): Response<YtResponse>

}