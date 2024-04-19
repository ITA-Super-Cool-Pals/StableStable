package com.example.stablestable.ui

sealed class Screen(val route: String) {
    object LoginScreen: Screen(route = "login_screen")
}
