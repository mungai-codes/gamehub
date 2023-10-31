package com.mungaicodes.gamehub.presentation.details

import com.mungaicodes.gamehub.domain.model.GameDetails

sealed interface DetailsScreenEvent {
    data class AddGameToFavourites(val game: GameDetails) : DetailsScreenEvent
    data object RemoveGameFromFavourites : DetailsScreenEvent
    data class OnGameClick(val gameId: String) : DetailsScreenEvent
    data object OnRetryLoad : DetailsScreenEvent
}