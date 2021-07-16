package com.karis.videoozone.ui.fragments.videolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.karis.videoozone.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoListViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

        val trendingVideos = mainRepository.getVideos().flow.cachedIn(viewModelScope).asLiveData()
}