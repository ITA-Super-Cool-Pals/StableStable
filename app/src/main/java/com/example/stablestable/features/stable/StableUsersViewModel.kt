package com.example.stablestable.features.stable

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.stablestable.firebase.AccountService

class StableUsersViewModel: ViewModel() {
    private val accountService: AccountService = AccountService()
    var nameList = mutableStateListOf<String>()



    private fun fetchUserList(){
        accountService.fetchAllUserData(onSuccess = {l:List<Map<String,Any>> ->
            for (map in l) {
                val firstname = map["firstname"] as String
                val lastname = map["lastname"] as String
                nameList.add("$firstname $lastname")
            }

        }, onFailure={s:String -> Log.d(TAG,s)})
    }
    init {
        fetchUserList()
        Log.d(TAG,"Success")
    }

}
