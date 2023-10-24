package com.mungaicodes.gamehub.presentation.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungaicodes.gamehub.R
import com.mungaicodes.gamehub.domain.model.Game
import com.mungaicodes.gamehub.presentation.components.shimmerEffect
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage
import kotlin.random.Random

@Composable
fun TrendingGame(
    game: Game,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .width(240.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Surface(
            Modifier
                .height(160.dp)
                .clickable { onClick() },
            shape = MaterialTheme.shapes.medium
        ) {
            CoilImage(
                imageModel = { game.backgroundImage },
                modifier = Modifier
                    .fillMaxSize(),
                imageOptions = ImageOptions(
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    contentDescription = game.name
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
        Text(
            text = game.name,
            fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
            textAlign = TextAlign.Start,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 13.sp
        )
    }
}

@Composable
fun TrendingGameShimmer(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    contentAfterLoading: @Composable () -> Unit,
) {

    if (isLoading) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Surface(
                modifier = Modifier
                    .size(150.dp, 15.dp)
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
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                items(2) {
                    Column(
                        modifier = modifier
                            .width(240.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Surface(
                            modifier = Modifier
                                .height(160.dp)
                                .fillMaxWidth(),
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .shimmerEffect()
                            )
                        }
                        Surface(
                            modifier = Modifier
                                .height(15.dp)
                                .size(Random.nextInt(80, 180).dp, 15.dp),
                            shape = MaterialTheme.shapes.extraSmall
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .shimmerEffect()
                            )
                        }
                    }
                }
            }
        }
    } else {
        contentAfterLoading()
    }

}