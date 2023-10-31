package com.mungaicodes.gamehub.presentation.details.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExpandLess
import androidx.compose.material.icons.outlined.ExpandMore
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungaicodes.gamehub.R
import com.mungaicodes.gamehub.domain.model.Achievement
import com.mungaicodes.gamehub.util.parseHtml
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun Achievement(
    achievement: Achievement,
    modifier: Modifier = Modifier
) {
    var showDescription by remember {
        mutableStateOf(false)
    }

    Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Surface(
            modifier = modifier,
            shape = MaterialTheme.shapes.medium,
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primaryContainer)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.wrapContentSize(),
                    shape = MaterialTheme.shapes.small
                ) {
                    CoilImage(
                        imageModel = { achievement.image },
                        modifier = Modifier.size(60.dp),
                        imageOptions = ImageOptions(
                            alignment = Alignment.Center,
                            contentDescription = achievement.name,
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
                }
                Spacer(modifier = Modifier.width(4.dp))
                Column(modifier = Modifier.weight(0.5f)) {
                    Text(
                        text = achievement.name,
                        fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = "Completion %: ${achievement.percent ?: "N/A"}",
                        fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontSize = 13.sp
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                IconButton(onClick = { showDescription = !showDescription }) {
                    Icon(
                        imageVector = if (showDescription) Icons.Outlined.ExpandLess else Icons.Outlined.ExpandMore,
                        contentDescription = null
                    )
                }
            }
        }
        AnimatedVisibility(visible = showDescription) {
            Text(
                text = achievement.description.parseHtml(),
                modifier = Modifier
                    .fillMaxWidth(),
                fontFamily = FontFamily(Font(R.font.tiltneon_regular))
            )
        }
    }
}