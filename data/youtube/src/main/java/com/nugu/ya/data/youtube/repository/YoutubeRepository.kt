package com.nugu.ya.data.youtube.repository

import androidx.paging.PagingData
import com.nugu.ya.core.model.YoutubeChannel
import kotlinx.coroutines.flow.Flow

interface YoutubeRepository {
    fun searchChannels(query: String) : Flow<PagingData<YoutubeChannel>>
}