package com.mungaicodes.gamehub.presentation.search

sealed interface SearchScreenUiEvent {
    data class NavigateToDetails(val gameId: String) : SearchScreenUiEvent

}