package com.example.stablestable.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.stablestable.firebase.AccountService


/*
 * Login Viewmodel
 * Code by Emily
 */

class LoginViewModel : ViewModel() {
    private val accountService: AccountService = AccountService()

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
    fun userCreate(navigateOnSuccess: () -> Unit, navigateOnFailure: () -> Unit) {
        // Check if fields are empty, show error message if they are
        if (email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty()) {
            createUserErrorMessage = "Alle felter skal udfyldes"
            return
        }

        // Create user
        accountService.userCreate(email, password,
            onResult = {
                accountService.createUserInFirestore(firstName, lastName, phone)
                navigateOnSuccess()
            },
            onFailure = { errorMessage ->
                createUserErrorMessage = errorMessage
                navigateOnFailure()
            })

    }

    fun userLogin(navigateOnSuccess: () -> Unit, navigateOnFailure: (String) -> Unit) {
        // Check if fields are empty, show error message if they are
        if (email.isEmpty() || password.isEmpty()) {
            loginErrorMessage = "Email og kodeord påkrævet"
            return
        }

        // Login user
        accountService.userLogin(email, password, navigateOnSuccess, navigateOnFailure)
    }
}
