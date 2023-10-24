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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mungaicodes.gamehub.R
import com.mungaicodes.gamehub.presentation.home.components.PopularGame
import com.mungaicodes.gamehub.presentation.home.components.PopularGameShimmer
import com.mungaicodes.gamehub.presentation.home.components.TrendingGame
import com.mungaicodes.gamehub.presentation.home.components.TrendingGameShimmer

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val state = viewModel.state.collectAsState().value
    HomeScreenContent(state)
}

@Composable
fun HomeScreenContent(
    state: HomeUiState
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
                TrendingGameShimmer(
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
                        if (state.error == null) {
                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                contentPadding = PaddingValues(horizontal = 16.dp),
                                horizontalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                items(state.trendingGames, key = { it.id }) { game ->
                                    TrendingGame(game = game) {

                                    }
                                }
                            }
                        } else {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                            ) {
                                Text(
                                    text = state.error,
                                    modifier = Modifier.align(Alignment.Center),
                                    fontFamily = FontFamily(Font(R.font.pixelifysans)),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            item {
                PopularGameShimmer(isLoading = state.loadingPopularGames) {
                    if (state.error == null) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(
                                    text = "all-time Popular",
                                    modifier = Modifier.padding(horizontal = 16.dp),
                                    fontFamily = FontFamily(Font(R.font.pixelifysans)),
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Column(
                                Modifier.padding(horizontal = 16.dp),
                                verticalArrangement = Arrangement
                                    .spacedBy(6.dp)
                            ) {
                                state.popularGames.take(5).forEach { game ->
                                    PopularGame(game = game) {

                                    }
                                }
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp)
                        ) {
                            Text(
                                text = state.error,
                                modifier = Modifier.align(Alignment.Center),
                                fontFamily = FontFamily(Font(R.font.pixelifysans)),
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(
                            text = "What you like most",
                            modifier = Modifier.padding(horizontal = 16.dp),
                            fontFamily = FontFamily(Font(R.font.pixelifysans)),
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

        }

    }
}