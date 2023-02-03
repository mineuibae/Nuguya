package com.nugu.ya.data.youtube.datasource.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nugu.ya.core.model.YoutubeChannel
import com.nugu.ya.data.youtube.datasource.remote.response.asModel

class YoutubeChannelPagingSource(
    private val service: YoutubeService,
    private val query: String
) : PagingSource<String, YoutubeChannel>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, YoutubeChannel> {
        val nextPageToken = params.key
        return try {
            val response = service.search(
                part = "snippet",
                pageToken = nextPageToken,
                q = query,
                type = "channel"
            )

            LoadResult.Page(
                data = response.items.map { it.asModel() },
                prevKey = null,
                nextKey = response.nextPageToken
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, YoutubeChannel>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}