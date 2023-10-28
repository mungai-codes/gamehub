package com.mungaicodes.gamehub.presentation.home

import com.mungaicodes.gamehub.domain.model.FavouriteGame
import com.mungaicodes.gamehub.domain.model.Game

data class HomeScreenUiState(
    val loadingTrendingGames: Boolean = false,
    val loadingPopularGames: Boolean = false,
    val loadingFavouriteGames: Boolean = false,
    val trendingGames: List<Game> = emptyList(),
    val popularGames: List<Game> = emptyList(),
    val favouriteGames: List<FavouriteGame> = emptyList(),
    val trendingGamesError: String? = null,
    val popularGamesError: String? = null,
    val favouriteGamesError: String? = null,
)
