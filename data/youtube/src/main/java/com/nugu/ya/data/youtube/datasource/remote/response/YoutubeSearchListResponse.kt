package com.nugu.ya.data.youtube.datasource.remote.response

import com.nugu.ya.core.model.YoutubeChannel
import kotlinx.serialization.Serializable

@Serializable
data class YoutubeSearchListResponse(
    val items: List<Item>,
    val nextPageToken: String? = null,
    val prevPageToken: String? = null
)

@Serializable
data class Item(val snippet: Snippet)

@Serializable
data class Snippet(
    val channelId: String,
    val channelTitle: String,
    val description: String,
    val thumbnails: Thumbnails,
)

@Serializable
data class Thumbnails(
    val default: Default,
    val high: High,
    val medium: Medium
)

@Serializable
data class Default(val url: String)

@Serializable
data class High(val url: String)

@Serializable
data class Medium(val url: String)

fun Item.asModel() = YoutubeChannel(
    channelId = snippet.channelId,
    channelTitle = snippet.channelTitle,
    description = snippet.description,
    thumbnail = snippet.thumbnails.default.url
)