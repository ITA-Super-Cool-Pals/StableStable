package com.example.stablestable.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stablestable.ui.calendar.CalendarScreen
import com.example.stablestable.ui.home.HomeScreen
import com.example.stablestable.ui.login.LoginScreen
import com.example.stablestable.ui.profile.MyProfileScreen
import com.example.stablestable.ui.stable.StableScreen
import com.example.stablestable.components.StableUsers
import com.example.stablestable.ui.horses.HorseCreateScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(
        navController = navController,
        //startDestination = Screen.HorseCreateScreen.route
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
            MyProfileScreen(

            )
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
