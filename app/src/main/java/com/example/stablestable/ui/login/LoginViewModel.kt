package com.example.stablestable.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.stablestable.firebase.AccountService

class LoginViewModel : ViewModel() {
    private val accountService: AccountService = AccountService()

    // Define username and password properties with initial empty strings
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var fullName by mutableStateOf("")
    var phone by mutableStateOf("")

    // Create user function
    fun userCreate(navigateOnSuccess: () -> Unit, navigateOnFailure: () -> Unit) {
        accountService.userCreate(email, password, fullName, phone,
            onResult = {
                accountService.createUserInFirestore(fullName, phone)
                navigateOnSuccess()
            },
            onFailure = {
                navigateOnFailure()
            })
    }

    fun userLogin(navigateOnSuccess: () -> Unit, navigateOnFailure: () -> Unit) {
        accountService.userLogin(email, password, navigateOnSuccess, navigateOnFailure)
    }
}
