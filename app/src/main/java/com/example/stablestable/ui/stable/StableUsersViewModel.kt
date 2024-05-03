package com.example.stablestable.ui.stable

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
                val firstName = map["firstName"] as String
                val lastName = map["lastName"] as String
                nameList.add("$firstName $lastName")
            }

        }, onFailure={s:String -> Log.d(TAG,s)})
    }
    init {
        fetchUserList()
        Log.d(TAG,"Success")
    }

}
