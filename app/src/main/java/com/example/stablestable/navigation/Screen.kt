package com.example.stablestable.navigation

sealed class Screen(val route: String) {
    object LoginScreen: Screen(route = "login_screen")
    object HomeScreen: Screen(route = "home_screen")
    object StableUsers: Screen(route = "stableUsers_screen")
    object UserProfileScreen: Screen(route = "userProfile/{userId}")
    object StableHorses: Screen(route = "stableHorses_screen")
    object HorseProfileScreen: Screen(route = "horseProfile/{horseId}")
    object StableScreen: Screen(route = "stable_screen")
    object CalendarScreen: Screen(route = "calendar_screen")

}
