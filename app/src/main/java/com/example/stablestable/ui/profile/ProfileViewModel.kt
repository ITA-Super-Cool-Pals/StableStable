package com.example.stablestable.ui.profile

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stablestable.data.repositories.impl.AccountServiceImpl
import com.example.stablestable.firebase.AccountService
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
                fullName = accountService.getCurrentUser()?.firstName ?: ""
                phone = accountService.getCurrentUser()?.phone ?: ""
                email = accountService.getCurrentUser()?.email ?: ""

            } catch (e:Exception){
                fullName = e.message.toString()
            }
        }
    }


}
