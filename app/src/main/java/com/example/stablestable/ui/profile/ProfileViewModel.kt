package com.example.stablestable.ui.profile

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.stablestable.firebase.AccountService


class ProfileViewModel: ViewModel() {

    init {
        fetchOne()
    }

    private val accountService: AccountService = AccountService()
    // This should be looked at if database change structure
    var fullname by mutableStateOf("")
    var phone by mutableStateOf("")
    var mail by mutableStateOf("")


    // Fetch current user details and pass it to ViewModel
    fun fetchOne() {
        accountService.fetchUserData(onSuccess = {r: Map<String, Any> ->
            fullname = r["fullname"].toString()
            phone = r["Phone"].toString()
            mail = r["mail"].toString()

        }, onFailure = {Log.d(TAG,"faliure")})

    }



}
