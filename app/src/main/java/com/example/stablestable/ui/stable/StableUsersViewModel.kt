package com.example.stablestable.ui.stable

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stablestable.data.classes.UserProfile
import com.example.stablestable.data.repositories.impl.AccountServiceImpl
import com.example.stablestable.navigation.AuthViewModel
import kotlinx.coroutines.launch

class StableUsersViewModel: ViewModel() {
    private val authViewModel: AuthViewModel = AuthViewModel()
    private val accountService: AccountServiceImpl = AccountServiceImpl()
    var nameList = mutableStateListOf<String>()
    private var stableId: String? = null

    private fun fetchUserList(){
        viewModelScope.launch {
            try {
                if (stableId != null) {
                    val userList: List<UserProfile?> = accountService.getAllUsersInStable(stableId!!)

                    userList.forEach { elem ->
                        if (elem != null) {
                            nameList.add("${elem.firstName} ${elem.lastName}")
                        }
                    }
                }
            } catch (e: Exception){
                Log.d(TAG,"Message: ${e.message.toString()}")
            }
        }
    }

    init {
        viewModelScope.launch {
            authViewModel.currentUserProfile.collect { currentUser ->
                if (currentUser != null) {
                    stableId = currentUser.stableId
                    fetchUserList()
                }
            }
        }
    }

}
