package com.example.stablestable.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.stablestable.data.classes.UserProfile
import com.example.stablestable.data.repositories.impl.AccountServiceImpl
import com.example.stablestable.firebase.AccountService

class LoginViewModel : ViewModel() {
    private val accountService: AccountService = AccountService()
    private val userRepo: AccountServiceImpl = AccountServiceImpl()

    // State variable to hold error message
    var loginErrorMessage by mutableStateOf("")
    var createUserErrorMessage by mutableStateOf("")

    // Define username and password properties with initial empty strings
    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var firstName by mutableStateOf("")
    var lastName by mutableStateOf("")
    var phone by mutableStateOf("")

    fun checkInput():Boolean{
        return email.isNotEmpty() && password.isNotEmpty() && firstName.isNotEmpty() && lastName.isNotEmpty() && phone.isNotEmpty()
    }

    // Create user function
    fun userCreate(navigateOnSuccess: () -> Unit, navigateOnFailure: () -> Unit) {
        if (email.isNotEmpty() && password.isNotEmpty() && firstName.isNotEmpty() && lastName.isNotEmpty() && phone.isNotEmpty()) {
            accountService.userCreate(email, password,
                onResult = {
                    accountService.createUserInFirestore(firstName, lastName, phone)
                    navigateOnSuccess()
                },
                onFailure = { errorMessage ->
                    createUserErrorMessage = errorMessage
                    navigateOnFailure()
                })
        } else {
            createUserErrorMessage = "All fields are required to be filled"
        }

    }
    suspend fun userCreateBranchAlt(){
        if (checkInput()){
            val newUser: UserProfile = UserProfile(firstName,lastName,email,phone,"69")
            userRepo.createUser(newUser,password)
        }

    }

    fun userLogin(navigateOnSuccess: () -> Unit, navigateOnFailure: (String) -> Unit) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            accountService.userLogin(email, password, navigateOnSuccess, navigateOnFailure)
        } else {
            loginErrorMessage = "Email and password are required"
        }
    }
}
