package com.mungaicodes.gamehub.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
) {

    Box(modifier = modifier.height(240.dp)) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp),
            shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CoilImage(
                    imageModel = { gameDetails.additionalBackgroundImage },
                    modifier = Modifier
                        .fillMaxSize(),
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
                        .fillMaxSize()
                        .background(
                            brush = Brush.radialGradient(
                                listOf(
                                    MaterialTheme.colorScheme.background.copy(0.4f),
                                    MaterialTheme.colorScheme.background.copy(0.5f),
                                    MaterialTheme.colorScheme.background.copy(0.6f)
                                )
                            )
                        )
                )
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
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
                .padding(horizontal = 16.dp)
                .align(Alignment.BottomStart),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier
                    .size(140.dp, 160.dp),
                shape = MaterialTheme.shapes.medium
            ) {
                CoilImage(
                    imageModel = { gameDetails.backgroundImage },
                    modifier = Modifier
                        .fillMaxSize(),
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
            Text(
                text = gameDetails.name,
                fontFamily = FontFamily(Font(R.font.kurale_regular)),
                fontWeight = FontWeight.Bold,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                fontSize = 22.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
