package com.mungaicodes.gamehub.presentation.genre

import com.mungaicodes.gamehub.domain.model.Game
import com.mungaicodes.gamehub.domain.model.Genre

data class GenreScreenUiState(
    val genreId: Int = 0,
    val loading: Boolean = false,
    val error: String? = null,
    val genre: Genre? = null,
    val games: List<Game> = emptyList(),
    val loadingGames: Boolean = false,
    val gameError: String? = null,
    val currentGenre: String = ""
)
