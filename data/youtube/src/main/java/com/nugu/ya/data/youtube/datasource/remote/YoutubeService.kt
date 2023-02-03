package com.nugu.ya.data.youtube.datasource.remote

import com.nugu.ya.data.youtube.BuildConfig
import com.nugu.ya.data.youtube.datasource.remote.response.YoutubeSearchListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeService {

    @GET("search")
    suspend fun search(
        @Query("part") part: String,
        @Query("pageToken") pageToken: String?,
        @Query("q") q: String,
        @Query("type") type: String,
        @Query("key") key: String = BuildConfig.API_KEY
    ): YoutubeSearchListResponse
}