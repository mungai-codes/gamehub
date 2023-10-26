package com.mungaicodes.gamehub.presentation.details

import com.mungaicodes.gamehub.domain.model.GameDetails
import com.mungaicodes.gamehub.presentation.home.HomeScreenEvent

sealed interface DetailsScreenEvent {
    data object GoBack : DetailsScreenEvent
    data class AddGameToFavourites(val game: GameDetails) : DetailsScreenEvent
    data class OnGameClick(val gameId: String) : DetailsScreenEvent
}