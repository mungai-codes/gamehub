package com.mungaicodes.gamehub.presentation.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
import com.mungaicodes.gamehub.domain.model.Creator
import com.mungaicodes.gamehub.presentation.components.DataCard
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun CreatorCard(
    creator: Creator,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            Modifier.size(150.dp, 250.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Box {
                CoilImage(
                    imageModel = { creator.imageBackground },
                    modifier = Modifier
                        .matchParentSize(),
                    imageOptions = ImageOptions(
                        alignment = Alignment.Center,
                        contentDescription = creator.name,
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
                Surface(
                    Modifier
                        .size(100.dp)
                        .align(Alignment.Center),
                    shape = CircleShape
                ) {
                    CoilImage(
                        imageModel = { creator.image},
                        modifier = Modifier
                            .matchParentSize(),
                        imageOptions = ImageOptions(
                            alignment = Alignment.Center,
                            contentDescription = creator.name,
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
                DataCard(
                    rating = creator.gamesCount.toString() + " Games",
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(top = 6.dp, end = 6.dp)
                )
            }

        }
        Text(
            text = creator.name,
            fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            fontSize = 13.sp
        )
    }
}