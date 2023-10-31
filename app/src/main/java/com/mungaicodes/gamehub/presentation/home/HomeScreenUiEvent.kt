package com.mungaicodes.gamehub.presentation.home

sealed interface HomeScreenUiEvent {
    data class NavigateToDetails(val gameId: String) : HomeScreenUiEvent
    data class NavigateToGenre(val genreId: Int, val genre: String) : HomeScreenUiEvent
}