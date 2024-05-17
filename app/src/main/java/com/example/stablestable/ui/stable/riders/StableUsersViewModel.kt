package com.example.stablestable.ui.stable.riders

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
    private var stableId: String? = null
    var userList = mutableStateListOf<Pair<String, String>>()

    private fun getUsers() {
        viewModelScope.launch {
            try {
                val users: List<Pair<String, UserProfile?>> = accountService.getAllUsersInStable(stableId!!)
                users.forEach { (userId, userProfile) ->
                    if (userProfile != null) {
                        userList.add(Pair(userId, "${userProfile.firstName} ${userProfile.lastName}"))
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, "Get Users Error: ${e.message.toString()}")
            }
        }
    }

    init {
        viewModelScope.launch {
            authViewModel.currentUserProfile.collect { currentUser ->
                if (currentUser != null) {
                    stableId = currentUser.stableId
                    getUsers()
                }
            }
        }
    }

}
