package com.mungaicodes.gamehub.presentation.details

import com.mungaicodes.gamehub.domain.model.GameDetails

sealed interface DetailsScreenEvent {
    data object GoBack : DetailsScreenEvent
    data class AddGameToFavourites(val game: GameDetails) : DetailsScreenEvent
}