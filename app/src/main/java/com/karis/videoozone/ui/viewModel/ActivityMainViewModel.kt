package com.karis.videoozone.ui.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.paging.liveData
import com.karis.videoozone.repository.MainRepository
import com.karis.videoozone.util.Coroutines.lazyDeferred
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ActivityMainViewModel @Inject constructor(private val mainRepository: MainRepository) : ViewModel() {

        val trendingVideos = mainRepository.getVideos().liveData
}