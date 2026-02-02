package com.example.habit_tracker_kotlin.layout

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.habit_tracker_kotlin.navigation.AppRoute
import com.example.habit_tracker_kotlin.navigation.bottomNavItems
import com.example.habit_tracker_kotlin.ui.screens.AccountScreen
import com.example.habit_tracker_kotlin.ui.screens.HistoryScreen
import com.example.habit_tracker_kotlin.ui.screens.HomeScreen

@Composable
fun MainLayout() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = AppRoute.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(AppRoute.Home.route) { HomeScreen() }
            composable(AppRoute.History.route) { HistoryScreen() }
            composable(AppRoute.Account.route) { AccountScreen() }
        }
    }
}

@Composable
private fun BottomNavBar(navController: NavHostController) {
    NavigationBar {
        val backStackEntry = navController.currentBackStackEntryAsState().value
        val currentRoute = backStackEntry?.destination?.route

        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) }
            )
        }
    }
}
