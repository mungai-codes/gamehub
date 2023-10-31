package com.mungaicodes.gamehub.presentation.genre.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mungaicodes.gamehub.R
import com.mungaicodes.gamehub.domain.model.Genre
import com.mungaicodes.gamehub.util.parseHtml
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun TopBar(
    genre: Genre,
    modifier: Modifier = Modifier
) {
    Surface(
        Modifier.wrapContentSize(),
        shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(240.dp)
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
            Row(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    Modifier
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = "Genre",
                        fontFamily = FontFamily(Font(R.font.pixelifysans)),
                        fontWeight = FontWeight.Bold,
                        fontSize = 25.sp,
                        color = MaterialTheme.colorScheme.primary,
                        textDecoration = TextDecoration.Underline
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = genre.name,
                        fontFamily = FontFamily(Font(R.font.kurale_regular)),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(6.dp))
                    genre.description?.let { description ->
                        Text(
                            text = description.parseHtml(),
                            modifier = Modifier.align(Alignment.Start),
                            fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

            }
        }
    }
}