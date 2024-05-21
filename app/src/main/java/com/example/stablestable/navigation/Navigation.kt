package com.example.stablestable.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.stablestable.ui.home.HomeScreen
import com.example.stablestable.ui.login.LoginScreen
import com.example.stablestable.ui.horses.HorseProfileScreen
import com.example.stablestable.ui.stable.horses.StableHorsesScreen
import com.example.stablestable.ui.profile.UserProfileScreen
import com.example.stablestable.ui.shifts.ShiftsScreen
import com.example.stablestable.ui.stable.riders.StableUsersScreen


@RequiresApi(Build.VERSION_CODES.O)
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
                goToRiders = { navController.navigate(Screen.StableUsers.route) },
                goToHome = { navController.navigate(Screen.HomeScreen.route) },
                goToShifts = { navController.navigate(Screen.ShiftsScreen.route) },
                //goToProfile = { navController.navigate(Screen.UserProfileScreen.route.replace("{userId}", authViewModel.userId ?: "")) },
                goToHorses = { navController.navigate(Screen.StableHorses.route) }
                //onLogout = { authViewModel.setUserLoggedIn(false) }
            )
        }

        // List of users in stable route
        composable(
            route = Screen.StableUsers.route
        ) {
            StableUsersScreen(
                onUserClick = {
                        userId -> navController.navigate(Screen.UserProfileScreen.route.replace("{userId}", userId))
                },
                goToRiders = { navController.navigate(Screen.StableUsers.route) },
                goToHome = { navController.navigate(Screen.HomeScreen.route) },
                goToShifts = { navController.navigate(Screen.ShiftsScreen.route) },
                goToHorses = { navController.navigate(Screen.StableHorses.route) }
            )
        }

        // List of horses in stable route
        composable(
            route = Screen.StableHorses.route
        ) {
            StableHorsesScreen(
                onHorseClick = {
                        horseId -> navController.navigate(Screen.HorseProfileScreen.route.replace("{horseId}", horseId))
                },
                goToRiders = { navController.navigate(Screen.StableUsers.route) },
                goToHome = { navController.navigate(Screen.HomeScreen.route) },
                goToShifts = { navController.navigate(Screen.ShiftsScreen.route) },
                goToHorses = { navController.navigate(Screen.StableHorses.route) }

            )
        }

        // My Profile Screen Route
        composable(
            route = Screen.UserProfileScreen.route,
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) {backStackEntry ->
            UserProfileScreen(
                userId = backStackEntry.arguments?.getString("userId") ?: "",
                onHorseClick = {
                        horseId -> navController.navigate(Screen.HorseProfileScreen.route.replace("{horseId}", horseId))
                },
                goToRiders = { navController.navigate(Screen.StableUsers.route) },
                goToHome = { navController.navigate(Screen.HomeScreen.route) },
                goToShifts = { navController.navigate(Screen.ShiftsScreen.route) },
                goToHorses = { navController.navigate(Screen.StableHorses.route) },
                onArrowBack = { if (navController.previousBackStackEntry != null){navController.navigateUp()}else { null }}


            )
        }

        // Horse Profile Screen Route
        composable(
            route = Screen.HorseProfileScreen.route,
                arguments = listOf(navArgument("horseId") { type = NavType.StringType })
        ) { backStackEntry ->
            HorseProfileScreen(
                horseId = backStackEntry.arguments?.getString("horseId") ?: "",
                goToRiders = { navController.navigate(Screen.StableUsers.route) },
                goToHome = { navController.navigate(Screen.HomeScreen.route) },
                goToShifts = { navController.navigate(Screen.ShiftsScreen.route) },
                goToHorses = { navController.navigate(Screen.StableHorses.route) },
                onArrowBack = { if (navController.previousBackStackEntry != null){navController.navigateUp()}else { null }}
            )
        }
        //Route to shifts
        composable(
            route = Screen.ShiftsScreen.route
        ){
            ShiftsScreen(
                goToHome = { navController.navigate(Screen.HomeScreen.route)},
                goToRiders = { navController.navigate(Screen.StableUsers.route) },
                goToShifts = { navController.navigate(Screen.ShiftsScreen.route) },
                goToHorses = { navController.navigate(Screen.StableHorses.route) }
            )
        }
    }
}
