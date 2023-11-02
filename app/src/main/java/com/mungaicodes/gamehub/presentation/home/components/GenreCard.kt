package com.mungaicodes.gamehub.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungaicodes.gamehub.R
import com.mungaicodes.gamehub.domain.model.Genre
import com.mungaicodes.gamehub.presentation.components.shimmerEffect
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun GenreCard(
    genre: Genre,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier
            .width(80.dp)
    ) {
        Surface(
            Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clickable { onClick() },
            shape = MaterialTheme.shapes.medium
        ) {
            CoilImage(
                imageModel = { genre.imageBackground },
                modifier = Modifier.fillMaxSize(),
                imageOptions = ImageOptions(
                    alignment = Alignment.Center,
                    contentDescription = genre.name,
                    contentScale = ContentScale.Crop
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
                    Box(
                        modifier = Modifier.matchParentSize(),
                        contentAlignment = Alignment.Center
                    ) {
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
        Text(
            text = genre.name,
            fontSize = 10.sp,
            fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            maxLines = 2
        )
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun FlowRowShimmer(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    if (isLoading) {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Surface(
                modifier = Modifier
                    .size(100.dp, 15.dp)
                    .padding(start = 16.dp),
                shape = MaterialTheme.shapes.extraSmall
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .shimmerEffect()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                repeat(19) {
                    Column(
                        Modifier
                            .width(80.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Surface(
                            Modifier
                                .fillMaxWidth()
                                .height(80.dp),
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .shimmerEffect()
                            )
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end = 6.dp)
                                .height(8.dp)
                                .shimmerEffect()
                        )
                    }
                }
            }
        }
    } else {
        content()
    }
}