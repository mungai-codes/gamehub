package com.mungaicodes.gamehub.presentation.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screens(val route: String) {
    data object Home : Screens("home_screen")
    data object Search : Screens("search_screen")
    data object Saved : Screens("saved_screen")
    data object Settings : Screens("settings_screen")
    data object Details : Screens("details_screen") {
        val routeWithArgs = "details_screen/{gameId}"
        val arguments = listOf(
            navArgument("gameId") {
                type = NavType.StringType
            }
        )
    }
}