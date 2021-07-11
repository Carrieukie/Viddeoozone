package com.karis.videoozone.data.retrofit

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.karis.videoozone.model.Videoitem
import java.util.*

class YoutubePagingSource(
    private val apiService: ApiService,
) : PagingSource<String, Videoitem>() {

    private val tokens = Stack<String>()

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Videoitem> {

        tokens.push(params.key)

        return try {
            val youtubeObject = apiService.fetchMostPopularVideos()

            LoadResult.Page(
                data = youtubeObject.body()!!.items,
                prevKey = tokens.pop(),
                nextKey = youtubeObject.body()?.nextPageToken
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<String, Videoitem>): String? {
        // Try to find the page key of the closest page to anchorPosition, from
        // either the prevKey or the nextKey, but you need to handle nullability
        // here:
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey null -> anchorPage is the initial page, so
        //    just return null.
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey ?: anchorPage?.nextKey

        }
    }
}