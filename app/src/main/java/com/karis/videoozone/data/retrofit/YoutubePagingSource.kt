package com.karis.videoozone.data.retrofit

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.karis.videoozone.model.Videoitem
import java.util.*

class YoutubePagingSource(
    private val apiService: ApiService,
) : PagingSource<Int, Videoitem>() {

    private  val TAG = "YoutubePagingSource"

    private val tokens = mutableListOf<String>()

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Videoitem> {

        return try {

            var page: Int = params.key ?: 0

            val token = if (tokens.size == 0) {
                null
            }else{
                tokens[page].also {
                    page += 1
                }
            }

            val youtubeObject = apiService.fetchMostPopularVideos(token)

            tokens.add(youtubeObject.body()?.nextPageToken!!)

            LoadResult.Page(
                data = youtubeObject.body()!!.items,
                prevKey = null, //Only page forward
                nextKey = page
            )


        } catch (e: Exception) {
            var em = e.message
            Log.e(TAG, "load: $em")
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Videoitem>): Int? {

        return state.anchorPosition

    }

}

