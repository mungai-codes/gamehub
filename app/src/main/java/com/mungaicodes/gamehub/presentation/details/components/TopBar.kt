package com.mungaicodes.gamehub.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.material.icons.outlined.DeleteSweep
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungaicodes.gamehub.R
import com.mungaicodes.gamehub.domain.model.GameDetails
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun TopBar(
    gameDetails: GameDetails,
    isFavourite: Boolean,
    modifier: Modifier = Modifier,
    onAddToFavourites: () -> Unit,
    onRemoveFromFavourites: () -> Unit,
    onBack: () -> Unit
) {
    Box(modifier = modifier.height(240.dp)) {
        Surface(
            modifier = Modifier
                .wrapContentSize(),
            shape = RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CoilImage(
                    imageModel = { gameDetails.additionalBackgroundImage },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp),
                    imageOptions = ImageOptions(
                        alignment = Alignment.Center,
                        contentDescription = gameDetails.name,
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
                            Text(
                                text = "image request failed.",
                                fontFamily = FontFamily(Font(R.font.kurale_regular))
                            )
                        }
                    }
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                listOf(
                                    MaterialTheme.colorScheme.background.copy(0.1f),
                                    MaterialTheme.colorScheme.background.copy(0.2f),
                                    MaterialTheme.colorScheme.background.copy(0.3f),
                                    MaterialTheme.colorScheme.background.copy(0.4f),
                                    MaterialTheme.colorScheme.background.copy(0.6f),
                                    MaterialTheme.colorScheme.background.copy(0.8f),
                                    MaterialTheme.colorScheme.background,
                                )
                            )
                        )
                        .align(Alignment.BottomStart)
                ) {
                    Row(
                        modifier = Modifier
                            .matchParentSize()
                            .padding(end = 16.dp),
                        verticalAlignment = Alignment.Top,
                        horizontalArrangement = Arrangement.End
                    ) {
                        Spacer(modifier = Modifier.weight(0.4f))
                        Text(
                            text = gameDetails.name,
                            modifier = Modifier.weight(0.5f),
                            fontFamily = FontFamily(Font(R.font.kurale_regular)),
                            fontWeight = FontWeight.Bold,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                            fontSize = 22.sp,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(top = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CircledIconButton(icon = Icons.Outlined.ArrowBack) {
                onBack()
            }
            if (isFavourite) {
                CircledIconButton(
                    icon = Icons.Outlined.DeleteSweep
                ) {
                    onRemoveFromFavourites()
                }

            } else {
                CircledIconButton(icon = Icons.Outlined.Bookmark) {
                    onAddToFavourites()
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
        ) {
            Surface(
                modifier = Modifier
                    .padding(horizontal = 16.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                CoilImage(
                    imageModel = { gameDetails.backgroundImage },
                    modifier = Modifier
                        .size(140.dp, 155.dp),
                    imageOptions = ImageOptions(
                        contentScale = ContentScale.Crop,
                        alignment = Alignment.Center,
                        contentDescription = gameDetails.name
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
    }
}