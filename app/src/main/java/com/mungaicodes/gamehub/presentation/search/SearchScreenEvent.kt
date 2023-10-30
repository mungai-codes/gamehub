package com.mungaicodes.gamehub.presentation.search

import com.mungaicodes.gamehub.presentation.home.HomeScreenEvent

sealed interface SearchScreenEvent {
    data object OnSearchEvent: SearchScreenEvent
    data class OnSearchQueryChanged(val query: String): SearchScreenEvent
    data class OnGameClick(val gameId: String) : SearchScreenEvent
}