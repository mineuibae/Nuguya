package com.nugu.ya

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nugu.ya.data.youtube.repository.YoutubeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: YoutubeRepository
): ViewModel() {

    fun searchChannels(query: String) = repository.searchChannels(query).cachedIn(viewModelScope)
}