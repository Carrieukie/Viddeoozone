package com.karis.videoozone.ui.fragments.videolist

import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.karis.videoozone.databinding.FragmentVideolistBinding
import com.karis.videoozone.model.Videoitem
import com.karis.videoozone.ui.ConnectivityLiveData.Connectivity
import com.karis.videoozone.util.interfaces.Onclick
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch


@AndroidEntryPoint
class VideoListFragment : Fragment(), Onclick {

    private lateinit var binding : FragmentVideolistBinding
    private val viewModel by viewModels<VideoListViewModel>()
    private lateinit var networkRequest: NetworkRequest
    private lateinit var adapter: MovieListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVideolistBinding.inflate(inflater,container,false)
        binding.recyclerViewVideos.layoutManager = LinearLayoutManager(requireContext())
        adapter = MovieListAdapter(this)
        binding.recyclerViewVideos.adapter = adapter
        networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .build()
        val connectivityManager = requireContext().getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

        Connectivity.isConnected.observe(viewLifecycleOwner, {
            when {
                true -> {
                    binding.recyclerViewVideos.visibility = View.VISIBLE
//                    noInternet.visibility = View.GONE
                    initializeRecyclerView()
                }
                false -> {
                    binding.recyclerViewVideos.visibility = View.GONE
//                    noInternet.visibility = View.INVISIBLE
                }
            }
        })

        connectivityManager.registerNetworkCallback(
            networkRequest,
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    Connectivity.isConnected.postValue(true)
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    Connectivity.isConnected.postValue(false)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    Connectivity.isConnected.postValue(false)
                }
            })
        return binding.root
    }



    private fun initializeRecyclerView() {

        lifecycleScope.launch {
            viewModel.trendingVideos.observe(viewLifecycleOwner, Observer {
                adapter.submitData(this@VideoListFragment.lifecycle,it)
            })
        }

    }

    override fun videoItemClicked(ytResponse: Videoitem) {

        val actions =VideoListFragmentDirections.videoListToWatching(ytResponse)
            findNavController().navigate(actions)
    }


}