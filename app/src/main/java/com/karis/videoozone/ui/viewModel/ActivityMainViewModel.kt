package com.karis.videoozone.ui.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.karis.videoozone.models.YtResponse
import com.karis.videoozone.repository.MainRepository
import com.karis.videoozone.util.Coroutines
import retrofit2.Response

class ActivityMainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) : ViewModel() {

    var youTubeVideos = MutableLiveData<Response<YtResponse>>()

    fun fetchMostWachedVideos(){
        Coroutines.io{
            youTubeVideos.postValue(mainRepository.fetchVideos())
        }

    }
}