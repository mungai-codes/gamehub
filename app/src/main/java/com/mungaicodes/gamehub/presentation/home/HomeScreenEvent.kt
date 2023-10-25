package com.mungaicodes.gamehub.presentation.home

sealed interface HomeScreenEvent {
    object TrendingGameClicked : HomeScreenEvent
    object PopularGameClicked : HomeScreenEvent
    object ShowMorePopularGames : HomeScreenEvent
}