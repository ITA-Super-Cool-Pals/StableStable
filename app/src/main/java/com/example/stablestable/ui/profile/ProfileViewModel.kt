package com.example.stablestable.ui.profile

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.stablestable.firebase.AccountService


class ProfileViewModel: ViewModel() {
    private val accountService: AccountService = AccountService()
    // This should be looked at if database change structure
    var fullname by mutableStateOf("")
    var phone by mutableStateOf("")
    var email by mutableStateOf("")

    init {
        // TODO: This call should be made on login, and save the profile to local memory. Possibly via Object
        fetchOne()
    }



    // Fetch current user details and pass it to ViewModel
    fun fetchOne() {
        accountService.fetchUserData(onSuccess = {r: Map<String, Any> ->
            fullname = r["fullname"].toString()
            phone = r["Phone"].toString()
            email = r["email"].toString()

        }, onFailure = {Log.d(TAG,"faliure")})

    }

    init {

        fetchOne()
    }
}
