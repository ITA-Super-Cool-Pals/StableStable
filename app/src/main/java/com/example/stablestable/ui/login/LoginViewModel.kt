package com.example.stablestable.ui.login

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.stablestable.R
import com.example.stablestable.data.classes.UserProfile
import com.example.stablestable.data.repositories.impl.AccountServiceImpl
import kotlinx.coroutines.launch


/*
 * Login Viewmodel
 * Code by Emily
 */

class LoginViewModel(application: Application): AndroidViewModel(application) {
    private val accountService: AccountServiceImpl = AccountServiceImpl()

    // Control visibility of user creation window
    var showCreateUserWindow by mutableStateOf(false)

    // State variable to hold error message
    var loginErrorMessage by mutableStateOf("")
    var createUserErrorMessage by mutableStateOf("")

    // Define username and password properties with initial empty strings
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var phone by mutableStateOf("")

    // Create user function
    fun userCreate(navigateOnSuccess: () -> Unit) {
        // Check if fields are empty, show error message if they are
        if (email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty()) {
            createUserErrorMessage = getApplication<Application>().getString(R.string.fillAllFields)
            return
        }

        // Create the user
        viewModelScope.launch {
            try {
                val userProfile = UserProfile(
                    firstName = firstName,
                    lastName = lastName,
                    email = email,
                    phone = phone
                )
                accountService.userCreate(userProfile, password)
                navigateOnSuccess()
            } catch (e: Exception) {
                createUserErrorMessage = e.message ?: "Account creation failed: Unknown error"
            }
        }
    }

    fun userLogin(navigateOnSuccess: () -> Unit) {
        // Check if fields are empty, show error message if they are
        if (email.isEmpty() || password.isEmpty()) {
            loginErrorMessage = getApplication<Application>().getString(R.string.fillAllFields)
            return
        }

        // Login the user
        viewModelScope.launch {
            try {
                accountService.userLogin(email, password)
                navigateOnSuccess()
            } catch (e: Exception) {
                loginErrorMessage = e.message ?: "Login failed: Unknown Error"
            }
        }
    }
}
