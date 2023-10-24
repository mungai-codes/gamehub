package com.mungaicodes.gamehub.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mungaicodes.gamehub.presentation.home.HomeScreen

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavigationItem.Home.route,
        modifier = modifier
    ) {
        composable(route = BottomNavigationItem.Home.route) {
            HomeScreen()
        }

        composable(route = BottomNavigationItem.Search.route) {
            Column(modifier = Modifier.fillMaxSize()) {

            }
        }

        composable(route = BottomNavigationItem.Settings.route) {
            Column(modifier = Modifier.fillMaxSize()) {

            }
        }
    }
}