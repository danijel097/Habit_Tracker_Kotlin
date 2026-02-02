package com.example.habit_tracker_kotlin.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class AppRoute(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    data object Home : AppRoute("home", "Home", Icons.Filled.Home)
    data object History : AppRoute("history", "History", Icons.Filled.History)
    data object Account : AppRoute("account", "Account", Icons.Filled.AccountCircle)
}

val bottomNavItems = listOf(
    AppRoute.Home,
    AppRoute.History,
    AppRoute.Account
)
