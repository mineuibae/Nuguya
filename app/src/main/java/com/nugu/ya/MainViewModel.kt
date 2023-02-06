package com.nugu.ya

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.nugu.ya.domain.usecase.GetYoutubeChannelListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getYoutubeChannelListUseCase: GetYoutubeChannelListUseCase
): ViewModel() {

    fun searchChannels(query: String) = getYoutubeChannelListUseCase(query).cachedIn(viewModelScope)
}