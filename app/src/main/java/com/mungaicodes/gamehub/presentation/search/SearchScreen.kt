package com.mungaicodes.gamehub.presentation.search

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
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
        ResultGridShimmer(
            isLoading = state.loading,
            modifier = Modifier.padding(
                top = contentPadding.calculateTopPadding(),
                bottom = contentPadding.calculateBottomPadding()
            )
        ) {
            ResultGrid(
                items = state.searchResults, modifier = Modifier.padding(
                    top = contentPadding.calculateTopPadding(),
                    bottom = contentPadding.calculateBottomPadding()
                )
            ) { game ->
                ResultGridItem(
                    title = game.name,
                    imageUrl = game.backgroundImage,
                    onClick = { onEvent(SearchScreenEvent.OnGameClick(game.slug)) },
                )
            }
        }
    }
}