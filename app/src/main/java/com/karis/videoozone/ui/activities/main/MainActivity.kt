package com.karis.videoozone.ui.activities.main

import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import com.karis.videoozone.R
import com.karis.videoozone.model.Videoitem
import com.karis.videoozone.ui.ConnectivityLiveData.Connectivity
import com.karis.videoozone.ui.activities.watching.WatchingActivity
import com.karis.videoozone.ui.recycleradapter.MovieListAdapter
import com.karis.videoozone.ui.viewModel.ActivityMainViewModel
import com.karis.videoozone.util.Coroutines
import com.karis.videoozone.util.interfaces.Onclick
import dagger.hilt.android.AndroidEntryPoint

import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), Onclick {

    private val viewModel by viewModels<ActivityMainViewModel>()
    private lateinit var networkRequest: NetworkRequest
    private lateinit var adapter: MovieListAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerViewVideos.layoutManager = LinearLayoutManager(this)
        adapter = MovieListAdapter(this)
        recyclerViewVideos.adapter = adapter
        networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
        val connectivityManager =
            applicationContext.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        Connectivity.isConnected.observe(this, Observer {
            when {
                true -> {
                    recyclerViewVideos.visibility = View.VISIBLE
//                    noInternet.visibility = View.GONE
                    initializeRecyclerView()
                }
                false -> {
                    recyclerViewVideos.visibility = View.GONE
//                    noInternet.visibility = View.INVISIBLE
                }
            }
        })

        connectivityManager.registerNetworkCallback(
            networkRequest,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network?) {
                    super.onAvailable(network)
                    Connectivity.isConnected.postValue(true)
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    Connectivity.isConnected.postValue(false)
                }

                override fun onLost(network: Network?) {
                    super.onLost(network)
                    Connectivity.isConnected.postValue(false)
                }
            })

    }


    private fun initializeRecyclerView() {
        val trendingVideosList = arrayListOf<Videoitem>()
        Coroutines.main {
            viewModel.trendingVideos.await().observe(this, Observer {
                for (i in it.indices) {
                    trendingVideosList.add(i, it[i])
                }
                adapter.submitList(trendingVideosList)
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