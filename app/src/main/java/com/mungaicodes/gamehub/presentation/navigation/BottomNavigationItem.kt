package com.mungaicodes.gamehub.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Savings
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationItem(val label: String, val route: String, val icon: ImageVector) {
    object Home : BottomNavigationItem("Home", Destination.Home.route, Icons.Outlined.Home)
    object Search : BottomNavigationItem("Search", Destination.Search.route, Icons.Outlined.Search)
    object Settings :
        BottomNavigationItem("Settings", Destination.Settings.route, Icons.Outlined.Settings)
}