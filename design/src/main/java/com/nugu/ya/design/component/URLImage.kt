package com.nugu.ya.design.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import coil.compose.AsyncImage

@Composable
fun URLImage(
    url: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    placeholder: Painter? = null
) {
    AsyncImage(
        placeholder = placeholder,
        model = url,
        contentDescription = contentDescription,
        modifier = modifier
    )
}