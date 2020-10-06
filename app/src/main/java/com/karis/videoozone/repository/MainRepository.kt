package com.karis.videoozone.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karis.videoozone.data.database.AppDatabase
import com.karis.videoozone.data.network.ApiService
import com.karis.videoozone.data.network.SafeApiRequest
import com.karis.videoozone.models.Videoitem
import com.karis.videoozone.models.YtResponse
import com.karis.videoozone.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject


class MainRepository @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : SafeApiRequest(){

    private val trendingVideos = MutableLiveData<List<Videoitem>>()

    init {

        trendingVideos.observeForever {
            saveTrendinVideos(it)
        }

    }

    //Check if fetch is needed.
    private fun isFetchNeeded(): Boolean {
        return true
    }

    //Fetch data from the API if fetch is needed is true
    private suspend fun fetchTrendingVideos() {
        if (isFetchNeeded()) {
            val fetchedtrendingResponse = safeApiRequest { apiService.fetchMostPopularVideos() }
            trendingVideos.postValue(fetchedtrendingResponse.items as List<Videoitem>?)
        }
    }

    private fun saveTrendinVideos(skillLeaders: List<Videoitem>) {
        Coroutines.io {
            appDatabase.videosDao().saveAllVideos(skillLeaders)
        }
    }

    suspend fun getTrendingVideos(): LiveData<List<Videoitem>> {
        return withContext(Dispatchers.IO) {
            fetchTrendingVideos()
            appDatabase.videosDao().getAllVideos()
        }
    }


}