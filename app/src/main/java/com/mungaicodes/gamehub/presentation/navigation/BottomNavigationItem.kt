package com.mungaicodes.gamehub.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItem(val label: String, val route: String, val icon: ImageVector) {
    object Home : BottomNavigationItem("Home", Screens.Home.route, Icons.Outlined.Home)
    object Search : BottomNavigationItem("Search", Screens.Search.route, Icons.Outlined.Search)
    object Settings :
        BottomNavigationItem("Settings", Screens.Settings.route, Icons.Outlined.Settings)
}