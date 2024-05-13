package com.example.stablestable.ui.profile

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stablestable.data.classes.UserProfile
import com.example.stablestable.data.repositories.impl.AccountServiceImpl
import com.example.stablestable.navigation.AuthViewModel
import kotlinx.coroutines.launch


class ProfileViewModel: ViewModel() {
    private val authViewModel: AuthViewModel = AuthViewModel()

    // This should be looked at if database change structure
    var fullName by mutableStateOf("")
    var phone by mutableStateOf("")
    var email by mutableStateOf("")



    init {
        viewModelScope.launch {
            authViewModel.currentUserProfile.collect { currentUser ->
                if (currentUser != null) {
                    fullName = "${currentUser.firstName} ${currentUser.lastName}"
                    phone = currentUser.phone
                    email = currentUser.email
                }
            }
        }
    }

    var showHorseCreateWindow by mutableStateOf(false)

}
