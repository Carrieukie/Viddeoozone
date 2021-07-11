package com.karis.videoozone.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.karis.videoozone.data.retrofit.ApiService
import com.karis.videoozone.data.retrofit.YoutubePagingSource
import javax.inject.Inject


class MainRepository @Inject constructor(
    private val apiService: ApiService,
) {

    fun getVideos() = Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
        pagingSourceFactory = {YoutubePagingSource(apiService)}
    )

}