package com.mungaicodes.gamehub.presentation.details.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
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
            .size(260.dp, 200.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        CoilImage(
            imageModel = { screenshot.image },
            modifier = Modifier
                .fillMaxSize(),
            imageOptions = ImageOptions(
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                contentDescription = screenshot.image
            ),
            loading = {
                Box(modifier = Modifier.matchParentSize()) {
                    CircularProgressIndicator(
                        Modifier
                            .align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            },
            failure = {
                Text(
                    text = "image request failed.",
                    fontFamily = FontFamily(Font(R.font.kurale_regular))
                )
            }
        )
    }

}