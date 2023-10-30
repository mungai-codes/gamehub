package com.mungaicodes.gamehub.presentation.search

import com.mungaicodes.gamehub.domain.model.Game

data class SearchScreenUiState(
    val searchQuery: String = "",
    val loading: Boolean = false,
    val searchError: String? = null,
    val searchResults: List<Game> = emptyList(),
    val supportText: String = ""
)
