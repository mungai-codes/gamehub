package com.mungaicodes.gamehub.presentation.search

sealed interface SearchScreenEvent {
    data object OnSearchEvent : SearchScreenEvent
    data class OnSearchQueryChanged(val query: String) : SearchScreenEvent
    data class OnGameClick(val gameId: String) : SearchScreenEvent
}