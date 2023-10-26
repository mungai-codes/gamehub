package com.mungaicodes.gamehub.presentation.details

import com.mungaicodes.gamehub.domain.model.GameDetails

data class DetailsScreenUiState(
    val gameId: String? = null,
    val loading: Boolean = false,
    val error: String? = null,
    val gameDetails: GameDetails? = null
)
