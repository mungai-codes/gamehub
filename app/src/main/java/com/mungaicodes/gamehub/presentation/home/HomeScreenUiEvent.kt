package com.mungaicodes.gamehub.presentation.home

sealed interface HomeScreenUiEvent {
    data class NavigateToDetails(val gameId: String) : HomeScreenUiEvent
}