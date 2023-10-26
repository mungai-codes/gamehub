package com.mungaicodes.gamehub.presentation.home

sealed interface HomeScreenEvent {
    data class OnGameClick(val gameId: String) : HomeScreenEvent
}