package com.mungaicodes.gamehub.presentation.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.mungaicodes.gamehub.presentation.details.components.TopBar
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
            }
        }
    }

    DetailsScreenContent(state = state, onEvent = viewModel::onEvent)
}

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
                onAddToFavourites = { onEvent(DetailsScreenEvent.AddGameToFavourites(state.gameDetails)) },
                onBack = { onEvent(DetailsScreenEvent.GoBack) }
            )
        }
    ) { contentPadding ->
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(
                    top = contentPadding.calculateTopPadding(),
                    bottom = contentPadding.calculateBottomPadding()
                ),
            state = lazyListState
        ) {

        }

    }

}