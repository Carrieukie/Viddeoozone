package com.karis.videoozone.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.withStyledAttributes
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.karis.videoozone.R
import com.karis.videoozone.interfaces.Onclick
import com.karis.videoozone.models.Videoitem
import com.karis.videoozone.ui.recycleradapter.VideosAdapter
import com.karis.videoozone.ui.viewModel.ActivityMainViewModel
import com.karis.videoozone.util.Coroutines
import com.karis.videoozone.util.VideoItemuTIL
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Onclick {

    private val viewModel by viewModels<ActivityMainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerViewVideos.layoutManager = LinearLayoutManager(this)
        initializeRecyclerView()
    }

    private fun initializeRecyclerView(){
        val trendingVideosList = arrayListOf<Videoitem>()
        val adapter = VideosAdapter(trendingVideosList, this)

        Coroutines.main {
            viewModel.trendingVideos.await().observe(this, Observer {
                for (i in it.indices){
                    trendingVideosList.add(i,it[i])
                    recyclerViewVideos.adapter = adapter
                }
            })
        }

    }

    override fun videoItemClicked(ytResponse: Videoitem) {
        Intent(this, WatchingActivity::class.java).also {
            it.putExtra("ytResponse", ytResponse)
            startActivity(it)
        }
    }

}