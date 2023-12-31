package com.mungaicodes.gamehub.presentation.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.slideInVertically
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mungaicodes.gamehub.R


@Composable
fun BottomAppBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val items = listOf(
        BottomNavigationItem.Home,
        BottomNavigationItem.Search,
        BottomNavigationItem.Settings
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    AnimatedVisibility(
        visible = items.any { it.route == currentDestination?.route },
        enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
        exit = shrinkOut() + fadeOut()
    ) {
        NavigationBar(
            modifier = modifier
        ) {

            items.forEach { screen ->
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(imageVector = screen.icon, contentDescription = screen.label)
                    },
                    label = {
                        Text(
                            text = screen.label,
                            fontFamily = FontFamily(Font(R.font.pixelifysans))
                        )
                    },
                    alwaysShowLabel = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                )
            }
        }
    }
}