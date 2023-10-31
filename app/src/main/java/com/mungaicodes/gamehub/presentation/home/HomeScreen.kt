package com.mungaicodes.gamehub.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.RestartAlt
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mungaicodes.gamehub.R
import com.mungaicodes.gamehub.presentation.components.ColumnItemsShimmer
import com.mungaicodes.gamehub.presentation.components.GameCard
import com.mungaicodes.gamehub.presentation.components.GameCardShimmer
import com.mungaicodes.gamehub.presentation.home.components.PopularGame
import com.mungaicodes.gamehub.presentation.navigation.Screens
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState().value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is HomeScreenUiEvent.NavigateToDetails -> {
                    navController.navigate(Screens.Details.route + "/${event.gameId}")
                }
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.getFavouriteGames()
    }

    HomeScreenContent(state, viewModel::onEvent)
}

@Composable
fun HomeScreenContent(
    state: HomeScreenUiState,
    onEvent: (HomeScreenEvent) -> Unit
) {
    val lazyListState = rememberLazyListState()
    Scaffold(
        Modifier
            .fillMaxSize(),
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Text(
                    text = " Game + Hub",
                    fontFamily = FontFamily(Font(R.font.pixelifysans)),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    ) { contentPadding ->

        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    bottom = contentPadding.calculateBottomPadding()
                ),
            state = lazyListState,
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            item {
                GameCardShimmer(
                    isLoading = state.loadingTrendingGames
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Top Trending/New",
                            modifier = Modifier.padding(horizontal = 16.dp),
                            fontFamily = FontFamily(Font(R.font.pixelifysans)),
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        if (state.trendingGamesError == null) {
                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(state.trendingGames, key = { it.id }) { game ->
                                    GameCard(
                                        name = game.name,
                                        backgroundImage = game.backgroundImage
                                    ) {
                                        onEvent(HomeScreenEvent.OnGameClick(game.slug))
                                    }
                                }
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .height(100.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    IconButton(onClick = { onEvent(HomeScreenEvent.RetryTrendingLoad) }) {
                                        Icon(
                                            imageVector = Icons.Outlined.RestartAlt,
                                            contentDescription = null
                                        )
                                    }
                                    Text(
                                        text = state.trendingGamesError,
                                        fontFamily = FontFamily(Font(R.font.pixelifysans)),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        }
                    }
                }
            }

            item {
                GameCardShimmer(
                    isLoading = state.loadingFavouriteGames
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(
                                text = "Your faves",
                                modifier = Modifier.padding(horizontal = 16.dp),
                                fontFamily = FontFamily(Font(R.font.pixelifysans)),
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        if (state.favouriteGamesError == null) {
                            if (state.favouriteGames.isNotEmpty()) {
                                LazyRow(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentPadding = PaddingValues(horizontal = 16.dp),
                                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                                ) {
                                    items(state.favouriteGames, key = { it.id }) { game ->
                                        GameCard(
                                            name = game.name,
                                            backgroundImage = game.imageUrl
                                        ) {
                                            onEvent(HomeScreenEvent.OnGameClick(game.slug))
                                        }
                                    }
                                }
                            } else {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                ) {
                                    Text(
                                        text = "No favourite games added",
                                        modifier = Modifier.align(Alignment.Center),
                                        fontFamily = FontFamily(Font(R.font.pixelifysans)),
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                                    .height(200.dp)
                            ) {
                                Text(
                                    text = state.favouriteGamesError,
                                    modifier = Modifier.align(Alignment.Center),
                                    fontFamily = FontFamily(Font(R.font.pixelifysans)),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

//            item {
//                ColumnItemsShimmer(isLoading = state.loadingPopularGames) {
//
//                    Column(
//                        modifier = Modifier.fillMaxWidth()
//                    ) {
//                        Row(horizontalArrangement = Arrangement.SpaceBetween) {
//                            Text(
//                                text = "all-time Popular",
//                                modifier = Modifier.padding(horizontal = 16.dp),
//                                fontFamily = FontFamily(Font(R.font.pixelifysans)),
//                                fontWeight = FontWeight.Bold,
//                                color = MaterialTheme.colorScheme.primary
//                            )
//                        }
//                        Spacer(modifier = Modifier.height(16.dp))
//                        if (state.popularGamesError == null) {
//                            Column(
//                                Modifier.padding(horizontal = 16.dp),
//                                verticalArrangement = Arrangement
//                                    .spacedBy(8.dp)
//                            ) {
//                                state.popularGames.forEach { game ->
//                                    PopularGame(game = game) {
//                                        onEvent(HomeScreenEvent.OnGameClick(game.slug))
//                                    }
//                                }
//                            }
//                        } else {
//                            Box(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(16.dp)
//                                    .height(100.dp),
//                                contentAlignment = Alignment.Center
//                            ) {
//                                Column(
//                                    horizontalAlignment = Alignment.CenterHorizontally,
//                                    verticalArrangement = Arrangement.spacedBy(6.dp)
//                                ) {
//                                    IconButton(onClick = { onEvent(HomeScreenEvent.RetryPopularLoad) }) {
//                                        Icon(
//                                            imageVector = Icons.Outlined.RestartAlt,
//                                            contentDescription = null
//                                        )
//                                    }
//                                    Text(
//                                        text = state.popularGamesError,
//                                        fontFamily = FontFamily(Font(R.font.pixelifysans)),
//                                        fontWeight = FontWeight.Bold
//                                    )
//                                }
//                            }
//                        }
//                    }
//                }
//            }

        }

    }
}