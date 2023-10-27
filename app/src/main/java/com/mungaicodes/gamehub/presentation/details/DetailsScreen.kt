package com.mungaicodes.gamehub.presentation.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mungaicodes.gamehub.R
import com.mungaicodes.gamehub.presentation.components.DataCard
import com.mungaicodes.gamehub.presentation.components.GameCard
import com.mungaicodes.gamehub.presentation.details.components.Achievement
import com.mungaicodes.gamehub.presentation.details.components.CreatorCard
import com.mungaicodes.gamehub.presentation.details.components.Screenshot
import com.mungaicodes.gamehub.presentation.details.components.TopBar
import com.mungaicodes.gamehub.presentation.navigation.Screens
import com.mungaicodes.gamehub.util.parseHtml
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: DetailsViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                DetailsScreenUiEvent.NavigateBack -> {
                    navController.popBackStack()
                }

                is DetailsScreenUiEvent.NavigateToDetails -> {
                    navController.navigate(Screens.Details.route + "/${event.gameId}")
                }
            }
        }
    }

    DetailsScreenContent(state = state, onEvent = viewModel::onEvent)
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DetailsScreenContent(
    state: DetailsScreenUiState,
    onEvent: (DetailsScreenEvent) -> Unit
) {

    val lazyListState = rememberLazyListState()


    Scaffold(
        topBar = {
            TopBar(
                gameDetails = state.gameDetails ?: return@Scaffold,
                modifier = Modifier.padding(bottom = 6.dp),
                onAddToFavourites = { onEvent(DetailsScreenEvent.AddGameToFavourites(state.gameDetails)) },
                onBack = { onEvent(DetailsScreenEvent.GoBack) }
            )
        }
    ) { contentPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    bottom = contentPadding.calculateBottomPadding()
                )
        ) {
            state.gameDetails?.let { game ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (game.alternativeNames.isNotEmpty()) {
                            Text(
                                text = game.alternativeNames.joinToString(", "),
                                fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        } else {
                            Text(
                                text = game.slug,
                                fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.reddit_is_fun),
                                contentDescription = null,
                                modifier = Modifier.size(54.dp),
                                tint = Color(0xFFF76904)
                            )
                        }
                    }

                    Divider(
                        Modifier
                            .fillMaxWidth(),
                        thickness = 2.dp,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                LazyColumn(
                    modifier = Modifier,
                    state = lazyListState,
                    contentPadding = PaddingValues(vertical = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    item {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Column(horizontalAlignment = Alignment.Start) {
                                    DataCard(rating = game.rating.toString())
                                    Text(
                                        text = "rating",
                                        fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
                                        fontWeight = FontWeight.ExtraLight,
                                        fontSize = 13.sp
                                    )
                                }
                                Column(horizontalAlignment = Alignment.Start) {
                                    DataCard(rating = game.playtime.toString() + " hours")
                                    Text(
                                        text = "playtime",
                                        fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
                                        fontWeight = FontWeight.ExtraLight,
                                        fontSize = 13.sp
                                    )
                                }
                                Column(horizontalAlignment = Alignment.Start) {
                                    DataCard(rating = game.released ?: "N/A")
                                    Text(
                                        text = "release(d)",
                                        fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
                                        fontWeight = FontWeight.ExtraLight,
                                        fontSize = 13.sp
                                    )
                                }
                                Column(horizontalAlignment = Alignment.Start) {
                                    DataCard(rating = game.esrbRating?.slug ?: "N/A")
                                    Text(
                                        text = "esrb-rating",
                                        fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
                                        fontWeight = FontWeight.ExtraLight,
                                        fontSize = 13.sp
                                    )
                                }
                            }
                            Divider(
                                Modifier.fillMaxWidth(),
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    item {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "description",
                                fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
                                fontWeight = FontWeight.Light
                            )
                            Text(
                                text = game.description.parseHtml(),
                                modifier = Modifier
                                    .fillMaxWidth(),
                                fontFamily = FontFamily(Font(R.font.tiltneon_regular))
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Divider(
                                Modifier.fillMaxWidth(),
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    item {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "platforms",
                                fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
                                fontWeight = FontWeight.Light
                            )
                            FlowRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(16.dp),
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                game.platforms.forEach { platform ->
                                    DataCard(rating = platform.platform.name ?: "N/A")
                                }
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Divider(
                                Modifier.fillMaxWidth(),
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "development-team",
                                modifier = Modifier.padding(start = 16.dp),
                                fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
                                fontWeight = FontWeight.Light
                            )
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(state.creators, key = { it.id }) { creator ->
                                    CreatorCard(creator = creator)
                                }

                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Divider(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "screenshots",
                                modifier = Modifier.padding(start = 16.dp),
                                fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
                                fontWeight = FontWeight.Light
                            )
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(state.screenShots, key = { it.id }) { screenShot ->
                                    Screenshot(screenshot = screenShot)
                                }

                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Divider(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "related-games",
                                modifier = Modifier.padding(start = 16.dp),
                                fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
                                fontWeight = FontWeight.Light
                            )
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(state.relatedGames, key = { it.slug }) { game ->
                                    GameCard(game = game) {
                                        onEvent(DetailsScreenEvent.OnGameClick(game.slug))
                                    }
                                }

                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Divider(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                    item {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "additions/dlc",
                                modifier = Modifier.padding(start = 16.dp),
                                fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
                                fontWeight = FontWeight.Light
                            )
                            LazyRow(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(state.additions, key = { it.slug }) { game ->
                                    GameCard(game = game) {
                                        onEvent(DetailsScreenEvent.OnGameClick(game.slug))
                                    }
                                }

                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Divider(
                                Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp),
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }


                    item {
                        Column(
                            modifier = Modifier.padding(horizontal = 16.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = "in-game achievements",
                                fontFamily = FontFamily(Font(R.font.tiltneon_regular)),
                                fontWeight = FontWeight.Light
                            )
                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                state.achievements.forEach { achievement ->
                                    Achievement(achievement = achievement)
                                }
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Divider(
                                Modifier.fillMaxWidth(),
                                thickness = 1.dp,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                    }

                }
            }
        }
    }
}