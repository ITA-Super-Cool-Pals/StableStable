package com.example.stablestable.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.stablestable.ui.home.HomeScreen
import com.example.stablestable.ui.login.LoginScreen
import com.example.stablestable.ui.stable.StableUsersScreen
import com.example.stablestable.ui.horses.HorseProfileScreen
import com.example.stablestable.ui.stable.StableHorsesScreen
import com.example.stablestable.ui.profile.UserProfileScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    authViewModel: AuthViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
        // if (authViewModel.isLoggedIn.value) Screen.HomeScreen.route else Screen.LoginScreen.route
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
                goToProfile = { navController.navigate(Screen.UserProfileScreen.route.replace("{userId}", authViewModel.userId ?: "")) },
                //goToRiders = { navController.navigate(Screen.StableUsers.route) },
                //goToHorses = { navController.navigate(Screen.StableHorses.route) },
                onLogout = { authViewModel.setUserLoggedIn(false) }
            )
        }

        // List of users in stable route
        composable(
            route = Screen.StableUsers.route
        ) {
            StableUsersScreen(
                onUserClick = {
                        userId -> navController.navigate(Screen.UserProfileScreen.route.replace("{userId}", userId))
                }
            )
        }

        // List of horses in stable route
        composable(
            route = Screen.StableHorses.route
        ) {
            StableHorsesScreen(
                onHorseClick = {
                        horseId -> navController.navigate(Screen.HorseProfileScreen.route.replace("{horseId}", horseId))
                }
            )
        }

        // My Profile Screen Route
        composable(
            route = Screen.UserProfileScreen.route
        ) {backStackEntry ->
            UserProfileScreen(
                backStackEntry.arguments?.getString("userId") ?: "",
                onHorseClick = {
                        horseId -> navController.navigate(Screen.HorseProfileScreen.route.replace("{horseId}", horseId))
                }
            )
        }

        // Horse Profile Screen Route
        composable(
            route = Screen.HorseProfileScreen.route
        ) { backStackEntry ->
            HorseProfileScreen(backStackEntry.arguments?.getString("horseId") ?: "")
        }
    }
}
