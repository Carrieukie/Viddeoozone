package com.karis.videoozone.data.retrofit

import com.karis.videoozone.model.YoutubeObject
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{
    @GET("videos?part=snippet,statistics&key=AIzaSyCCR1FUBgD65VTZ3Ga0QBe8_bOvxlyIa9o&maxResults=200&chart=mostPopular")
    suspend fun fetchMostPopularVideos(
        @Query("nextPageToken") pageToken : String? = null
    ): Response<YoutubeObject>

}