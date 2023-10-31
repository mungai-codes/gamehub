package com.mungaicodes.gamehub.presentation.genre

sealed interface GenreScreenEvent {
    data class OnGameClick(val gameId: String) : GenreScreenEvent
    data object OnRetryClick : GenreScreenEvent
}