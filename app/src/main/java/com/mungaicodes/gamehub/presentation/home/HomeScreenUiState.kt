package com.mungaicodes.gamehub.presentation.home

import com.mungaicodes.gamehub.domain.model.FavouriteGame
import com.mungaicodes.gamehub.domain.model.Game
import com.mungaicodes.gamehub.domain.model.Genre

data class HomeScreenUiState(
    val loadingTrendingGames: Boolean = false,
    val loadingFavouriteGames: Boolean = false,
    val loadingGenres: Boolean = false,
    val trendingGames: List<Game> = emptyList(),
    val favouriteGames: List<FavouriteGame> = emptyList(),
    val genres: List<Genre> = emptyList(),
    val trendingGamesError: String? = null,
    val favouriteGamesError: String? = null,
    val genresError: String? = null
)
