package com.example.stablestable.navigation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stablestable.data.classes.UserProfile
import com.example.stablestable.data.repositories.impl.AccountServiceImpl
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/*
 * Viewmodel to help with authentication and redirection behaviour
 * If a user is authenticated, the startDestination will be HomeScreen
 * Otherwise it will be LoginScreen
 *
 * Code by Emily
 */

class AuthViewModel : ViewModel() {
    private val accountService: AccountServiceImpl = AccountServiceImpl()

    private val _isLoggedIn = mutableStateOf(false)
    val isLoggedIn: State<Boolean> = _isLoggedIn

    // Get the current authenticated user
    private var currentUser = Firebase.auth.currentUser
    var userId = currentUser?.uid

    // Set the user profile data
    private val _currentUserProfile = MutableStateFlow<UserProfile?>(null)
    val currentUserProfile: StateFlow<UserProfile?> = _currentUserProfile

    //Lykke
    private val _currentScreen = mutableStateOf<String?>(null)


    init {
        checkCurrentUser()
    }

    private fun checkCurrentUser() {
        currentUser = Firebase.auth.currentUser
        userId = currentUser?.uid
        _isLoggedIn.value = currentUser != null
        if (_isLoggedIn.value) {
            fetchUserProfile()
        }
    }

    private fun fetchUserProfile() {
        viewModelScope.launch {
            if (userId != null) {
                _currentUserProfile.value = accountService.getCurrentUser(userId!!)
            }
        }
    }

    fun setUserLoggedIn(isLoggedIn: Boolean) {
        _isLoggedIn.value = isLoggedIn
    }
    //Lykke
    fun updateCurrentScreen(screen: String) {
        _currentScreen.value = screen
    }

}
