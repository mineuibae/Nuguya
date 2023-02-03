package com.nugu.ya.data.youtube.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nugu.ya.data.youtube.datasource.remote.YoutubeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun providesYoutubeService(
        networkJson: Json,
        okHttpClient: OkHttpClient
    ) : YoutubeService {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://www.googleapis.com/youtube/v3/")
            .addConverterFactory(
                @OptIn(ExperimentalSerializationApi::class)
                networkJson.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(YoutubeService::class.java)
    }
}