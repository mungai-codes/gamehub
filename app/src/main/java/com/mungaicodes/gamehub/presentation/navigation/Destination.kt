package com.mungaicodes.gamehub.presentation.navigation

sealed class Destination(val route: String) {
    object Home : Destination("home_screen")
    object Saved : Destination("saved_screen")
    object Settings : Destination("settings_screen")
}