package com.mungaicodes.gamehub.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mungaicodes.gamehub.presentation.details.DetailsScreen
import com.mungaicodes.gamehub.presentation.home.HomeScreen
import com.mungaicodes.gamehub.presentation.search.SearchScreen

@Composable
fun Navigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Graphs.BottomBarGraph.route,
        modifier = modifier
    ) {
        bottomBarGraph(navController = navController)

        composable(
            route = Screens.Details.routeWithArgs,
            arguments = Screens.Details.arguments
        ) {
            DetailsScreen(navController = navController)
        }
    }
}

fun NavGraphBuilder.bottomBarGraph(navController: NavController) {
    navigation(
        startDestination = BottomNavigationItem.Home.route,
        route = Graphs.BottomBarGraph.route
    ) {
        composable(route = BottomNavigationItem.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(route = BottomNavigationItem.Search.route) {
            SearchScreen(navController = navController)
        }

        composable(route = BottomNavigationItem.Settings.route) {
            Column(modifier = Modifier.fillMaxSize()) {

            }
        }
    }
}