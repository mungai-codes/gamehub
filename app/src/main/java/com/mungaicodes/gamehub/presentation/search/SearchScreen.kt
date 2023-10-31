package com.mungaicodes.gamehub.presentation.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.RestartAlt
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mungaicodes.gamehub.R
import com.mungaicodes.gamehub.presentation.components.ResultGrid
import com.mungaicodes.gamehub.presentation.components.ResultGridItem
import com.mungaicodes.gamehub.presentation.components.ResultGridShimmer
import com.mungaicodes.gamehub.presentation.navigation.Screens
import com.mungaicodes.gamehub.presentation.search.components.TopBar
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is SearchScreenUiEvent.NavigateToDetails -> {
                    navController.navigate(Screens.Details.route + "/${event.gameId}")
                }
            }
        }
    }

    SearchScreenContent(
        state = viewModel.state.collectAsState().value,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun SearchScreenContent(
    state: SearchScreenUiState,
    onEvent: (SearchScreenEvent) -> Unit
) {

    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopBar(
                searchQuery = state.searchQuery,
                enabled = state.searchQuery.isNotBlank(),
                supportingText = state.supportText,
                onValueChange = { onEvent(SearchScreenEvent.OnSearchQueryChanged(it)) },
                onSearch = { onEvent(SearchScreenEvent.OnSearchEvent) }
            )
        }
    ) { contentPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    bottom = contentPadding.calculateBottomPadding()
                ),
            state = lazyListState,
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {

            item {
                ResultGridShimmer(isLoading = state.loading) {
                    if (state.searchError == null) {
                        if (state.searchResults.isEmpty()) {
                            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                                Text(
                                    text = "No results found!",
                                    fontFamily = FontFamily(Font(R.font.pixelifysans)),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        } else {
                            ResultGrid(items = state.searchResults) { game ->
                                ResultGridItem(
                                    title = game.name,
                                    imageUrl = game.backgroundImage,
                                    modifier = Modifier.width(160.dp),
                                    onClick = { onEvent(SearchScreenEvent.OnGameClick(game.slug)) })
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                IconButton(onClick = { onEvent(SearchScreenEvent.OnSearchEvent) }) {
                                    Icon(
                                        imageVector = Icons.Outlined.RestartAlt,
                                        contentDescription = null
                                    )
                                }
                                Text(
                                    text = state.searchError,
                                    fontFamily = FontFamily(Font(R.font.pixelifysans)),
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}