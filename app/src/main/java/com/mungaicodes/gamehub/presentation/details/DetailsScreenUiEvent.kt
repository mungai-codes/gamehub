package com.mungaicodes.gamehub.presentation.details

sealed interface DetailsScreenUiEvent {

    data class NavigateToDetails(val gameId: String) : DetailsScreenUiEvent
}