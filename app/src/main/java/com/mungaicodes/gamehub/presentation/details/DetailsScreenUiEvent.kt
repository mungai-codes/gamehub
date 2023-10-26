package com.mungaicodes.gamehub.presentation.details

import com.mungaicodes.gamehub.presentation.home.HomeScreenUiEvent

sealed interface DetailsScreenUiEvent {
    data object NavigateBack : DetailsScreenUiEvent

    data class NavigateToDetails(val gameId: String) : DetailsScreenUiEvent
}