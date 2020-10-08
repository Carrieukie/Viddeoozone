package com.karis.videoozone.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.karis.videoozone.data.room.AppDatabase
import com.karis.videoozone.data.retrofit.ApiService
import com.karis.videoozone.data.retrofit.SafeYoutubeRequest
import com.karis.videoozone.model.Videoitem
import com.karis.videoozone.util.Coroutines
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class MainRepository @Inject constructor(

    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : SafeYoutubeRequest(){

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

    private fun isInternetAvailable(context: Context ) : Boolean {
        var result = false

        val connectivityManager =  context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?

        connectivityManager?.let {
            it.getNetworkCapabilities(connectivityManager.activeNetwork)?.apply {
                result = when {
                    hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    else -> false
                }
            }
        }
        return result
    }


}