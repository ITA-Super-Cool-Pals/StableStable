package com.example.stablestable.navigation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

/*
 * Viewmodel to help with authentication and redirection behaviour
 * If a user is authenticated, the startDestination will be HomeScreen
 * Otherwise it will be LoginScreen
 *
 * Code by Emily
 */

class AuthViewModel : ViewModel() {
    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn: State<Boolean> = _isLoggedIn

    init {
        checkCurrentUser()
    }

    private fun checkCurrentUser() {
        val currentUser = Firebase.auth.currentUser
        _isLoggedIn.value = currentUser != null
    }

    fun setUserLoggedIn(isLoggedIn: Boolean) {
        _isLoggedIn.value = isLoggedIn
    }
}
