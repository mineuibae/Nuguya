package com.nugu.ya.data.youtube.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nugu.ya.core.model.YoutubeChannel
import com.nugu.ya.data.youtube.datasource.remote.YoutubeChannelPagingSource
import com.nugu.ya.data.youtube.datasource.remote.YoutubeService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CacheYoutubeRepository @Inject constructor(
    private val service: YoutubeService
) : YoutubeRepository {

    override fun searchChannels(query: String) : Flow<PagingData<YoutubeChannel>> {
        return Pager(
            config = PagingConfig(enablePlaceholders = false, pageSize = 1),
            pagingSourceFactory = { YoutubeChannelPagingSource(service, query) }
        ).flow
    }
}