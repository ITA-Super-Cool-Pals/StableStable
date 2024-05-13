package com.example.stablestable.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stablestable.ui.home.HomeScreen
import com.example.stablestable.ui.login.LoginScreen
import com.example.stablestable.ui.profile.MyProfileScreen
import com.example.stablestable.ui.riders.StableScreen
import com.example.stablestable.ui.riders.StableUsers

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(
        navController = navController,
        startDestination = if (authViewModel.isLoggedIn.value) Screen.HomeScreen.route else Screen.LoginScreen.route
    ) {
        // Login Screen Route
        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(
                onRegistrationSuccess = { authViewModel.setUserLoggedIn(true) }
            )
        }

        // Home Screen Route
        composable(
            route = Screen.HomeScreen.route
        ) {
            HomeScreen(
                goToProfile = { navController.navigate(Screen.MyProfileScreen.route) },
                goToRiders = { navController.navigate(Screen.StableUsers.route) },
                onLogout = { authViewModel.setUserLoggedIn(false) }
            )
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
            StableScreen()
        }

        composable(
            route = Screen.StableUsers.route
        ) {
            StableUsers()
        }
    }
}
