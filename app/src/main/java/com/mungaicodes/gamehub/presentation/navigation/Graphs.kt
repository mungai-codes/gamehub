package com.mungaicodes.gamehub.presentation.navigation

sealed class Graphs(val route: String) {
    data object BottomBarGraph : Graphs("bottom_bar_graph")
}