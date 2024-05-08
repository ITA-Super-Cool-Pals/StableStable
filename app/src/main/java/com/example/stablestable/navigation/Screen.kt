package com.example.stablestable.navigation

sealed class Screen(val route: String) {
    object LoginScreen: Screen(route = "login_screen")
    object HomeScreen: Screen(route = "home_screen")
    // TODO: Change to snake-case
    object MyProfileScreen: Screen(route = "myProfile_screen")
    object StableScreen: Screen(route = "stable_screen")
    object CalendarScreen: Screen(route = "calendar_screen")
    object StableUsers: Screen(route = "user_list")
    object ShiftsScreen: Screen(route = "shifts_screen")
}
