package com.mungaicodes.gamehub.presentation.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.mungaicodes.gamehub.R
import com.mungaicodes.gamehub.domain.model.Screenshot
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun Screenshot(
    screenshot: Screenshot,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .wrapContentSize(),
        shape = MaterialTheme.shapes.medium
    ) {
        CoilImage(
            imageModel = { screenshot.image },
            modifier = Modifier.size(260.dp, 200.dp),
            imageOptions = ImageOptions(
                alignment = Alignment.Center,
                contentDescription = screenshot.image,
                contentScale = ContentScale.Crop
            ),
            loading = {
                Box(
                    modifier = Modifier
                        .matchParentSize()
                ) {
                    CircularProgressIndicator(
                        Modifier
                            .align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            },
            failure = {
                Box(modifier = Modifier.matchParentSize(), contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.image_load_error),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        )
    }

}