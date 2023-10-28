package com.mungaicodes.gamehub.presentation.home

sealed interface HomeScreenEvent {
    data class OnGameClick(val gameId: String) : HomeScreenEvent
    data object RetryTrendingLoad : HomeScreenEvent
    data object RetryPopularLoad : HomeScreenEvent
}