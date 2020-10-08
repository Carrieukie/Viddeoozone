package com.karis.videoozone.ui.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.karis.videoozone.repository.MainRepository
import com.karis.videoozone.util.Coroutines.lazyDeferred

class ActivityMainViewModel @ViewModelInject constructor(private val mainRepository: MainRepository) : ViewModel() {
        val trendingVideos by lazyDeferred { mainRepository.getTrendingVideos()}
}