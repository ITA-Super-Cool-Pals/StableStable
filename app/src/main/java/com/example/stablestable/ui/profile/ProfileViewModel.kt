package com.example.stablestable.ui.profile

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.stablestable.firebase.AccountService


class ProfileViewModel: ViewModel() {
    private val accountService: AccountService = AccountService()

    var profilename:String = ""

    // Fetch current user details and pass it to ViewModel
    fun fetchOne() {
        accountService.fetchUserData(onSuccess = {s:String -> profilename = s}, onFailure = {Log.d(TAG,"faliure")})
    }


}
