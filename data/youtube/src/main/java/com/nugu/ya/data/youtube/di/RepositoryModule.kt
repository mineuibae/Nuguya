package com.nugu.ya.data.youtube.di

import com.nugu.ya.data.youtube.repository.CacheYoutubeRepository
import com.nugu.ya.data.youtube.repository.YoutubeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsYoutubeRepository(
        youtubeRepository: CacheYoutubeRepository
    ): YoutubeRepository
}