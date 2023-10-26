package com.mungaicodes.gamehub.presentation.details

sealed interface DetailsScreenUiEvent {
    data object NavigateBack : DetailsScreenUiEvent
}