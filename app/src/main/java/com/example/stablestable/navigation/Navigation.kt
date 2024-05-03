package com.example.stablestable.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stablestable.ui.Screen
import com.example.stablestable.ui.calendar.CalendarScreen
import com.example.stablestable.ui.home.HomeScreen
import com.example.stablestable.ui.login.LoginScreen
import com.example.stablestable.ui.profile.MyProfileScreen
import com.example.stablestable.ui.stable.StableScreen
import com.example.stablestable.components.StableUsers

@Composable
fun SetupNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.LoginScreen.route
    ) {
        // Login Screen Route

        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(
                onRegistrationSuccess = { navController.navigate(Screen.HomeScreen.route) },
                onRegistrationFailure = {}
            )
        }

        // Home Screen Route
        composable(
            route = Screen.HomeScreen.route
        ) {
            HomeScreen(navController = navController)
        }

        // My Profile Screen Route
        composable(
            route = Screen.MyProfileScreen.route
        ) {
            MyProfileScreen(navController = navController)
        }

        // Stable Screen Route
        composable(
            route = Screen.StableScreen.route
        ) {
            StableScreen(navController = navController)
        }

        // Calendar Screen Route
        composable(
            route = Screen.CalendarScreen.route
        ) {
            CalendarScreen(navController = navController)
        }

        composable(
            route = Screen.StableUsers.route
        ) {
            StableUsers(navController = navController)
        }
    }
}
