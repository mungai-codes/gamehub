package com.mungaicodes.gamehub.presentation.home

import com.mungaicodes.gamehub.domain.model.Game

data class HomeScreenUiState(
    val loadingTrendingGames: Boolean = false,
    val loadingPopularGames: Boolean = false,
    val loadingSavedGams: Boolean = false,
    val trendingGames: List<Game> = emptyList(),
    val popularGames: List<Game> = emptyList(),
    val error: String? = null,
    val preview: String = ""
)
