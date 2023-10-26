package com.mungaicodes.gamehub.presentation.details

import com.mungaicodes.gamehub.domain.model.Achievement
import com.mungaicodes.gamehub.domain.model.Game
import com.mungaicodes.gamehub.domain.model.GameDetails
import com.mungaicodes.gamehub.domain.model.Screenshot

data class DetailsScreenUiState(
    val gameId: String? = null,
    val loading: Boolean = false,
    val error: String? = null,
    val gameDetails: GameDetails? = null,
    val screenShots: List<Screenshot> = emptyList(),
    val achievements: List<Achievement> = emptyList(),
    val relatedGames: List<Game> = emptyList(),
    val additions: List<Game> = emptyList()
)
