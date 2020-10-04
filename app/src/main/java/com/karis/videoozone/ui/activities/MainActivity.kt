package com.karis.videoozone.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.karis.videoozone.R
import com.karis.videoozone.interfaces.Onclick
import com.karis.videoozone.models.ItemsItem
import com.karis.videoozone.ui.recycleradapter.VideosAdapter
import com.karis.videoozone.ui.viewModel.ActivityMainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Onclick {

    private val viewModel by viewModels<ActivityMainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewVideos.layoutManager = LinearLayoutManager(this)
        viewModel.youTubeVideos.observe(this, Observer {
            recyclerViewVideos.adapter = VideosAdapter(it.body()?.items as List<ItemsItem>,this)
        })

        viewModel.fetchMostWachedVideos()

    }

    override fun videoItemClicked(ytResponse: ItemsItem) {
        Intent(this, WatchingActivity::class.java).also {
            it.putExtra("ytResponse", ytResponse)
            startActivity(it)
        }
    }

}