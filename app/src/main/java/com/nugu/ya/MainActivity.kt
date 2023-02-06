package com.nugu.ya

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.nugu.ya.design.component.URLImage
import com.nugu.ya.ui.theme.NuguyaTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NuguyaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    YoutubeChannelList(viewModel)
                }
            }
        }
    }
}
@Composable
fun YoutubeChannelList(viewModel: MainViewModel) {
    val channels = viewModel.searchChannels("토스").collectAsLazyPagingItems()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(channels.itemCount) { item ->
            val channel = channels[item] ?: return@items
            Row(verticalAlignment = Alignment.CenterVertically) {
                URLImage(
                    url = channel.thumbnail,
                    contentDescription = channel.description,
                    modifier = Modifier.size(100.dp).padding(10.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = channel.channelTitle)
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}