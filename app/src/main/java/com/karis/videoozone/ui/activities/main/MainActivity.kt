package com.karis.videoozone.ui.activities.main

import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.karis.videoozone.R
import com.karis.videoozone.model.Videoitem
import com.karis.videoozone.ui.ConnectivityLiveData.Connectivity
import com.karis.videoozone.ui.activities.watching.WatchingActivity
import com.karis.videoozone.ui.viewModel.ActivityMainViewModel
import com.karis.videoozone.util.interfaces.Onclick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch


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

        lifecycleScope.launch {
            viewModel.trendingVideos.observe(this@MainActivity, Observer {
                adapter.submitData(this@MainActivity.lifecycle,it)
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