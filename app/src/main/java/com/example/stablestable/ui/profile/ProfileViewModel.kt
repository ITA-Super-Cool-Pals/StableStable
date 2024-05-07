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
import kotlinx.coroutines.launch


class ProfileViewModel: ViewModel() {
    private val accountService: AccountServiceImpl = AccountServiceImpl()
    // This should be looked at if database change structure
    var fullName by mutableStateOf("")
    var phone by mutableStateOf("")
    var email by mutableStateOf("")


    // Fetch current user details and pass it to ViewModel
    private fun getCurrentUser() {
        viewModelScope.launch{
             try {
                 val currentUser: UserProfile? = accountService.getCurrentUser()
                 if (currentUser != null) {
                     fullName = currentUser.firstName + currentUser.lastName
                     phone = currentUser.phone
                     email = currentUser.email
                 }

            } catch (e:Exception){
                Log.d(TAG,"Message: ${e.message.toString()}")
            }
        }
    }

    init {
        getCurrentUser()
    }

}
