package com.nugu.ya.domain.usecase

import androidx.paging.PagingData
import com.nugu.ya.core.model.YoutubeChannel
import com.nugu.ya.core.usecase.BaseUseCase
import com.nugu.ya.data.youtube.repository.YoutubeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetYoutubeChannelListUseCase @Inject constructor(
    private val youtubeRepository: YoutubeRepository
) : BaseUseCase<String, Flow<PagingData<YoutubeChannel>>>() {

    override fun execute(parameter: String): Flow<PagingData<YoutubeChannel>> {
        return youtubeRepository.searchChannels(parameter)
    }
}