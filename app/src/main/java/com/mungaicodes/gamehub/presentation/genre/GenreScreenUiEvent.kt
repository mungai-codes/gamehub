package com.mungaicodes.gamehub.presentation.genre

sealed interface GenreScreenUiEvent {

    data class NavigateToDetails(val gameId: String) : GenreScreenUiEvent
}