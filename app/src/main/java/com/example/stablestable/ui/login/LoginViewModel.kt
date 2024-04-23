package com.example.stablestable.ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    // Define username and password properties with initial empty strings
    var email by mutableStateOf("")
    var password by mutableStateOf("")
}
