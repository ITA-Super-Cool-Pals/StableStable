package com.example.stablestable.ui.profile

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stablestable.data.classes.HorseProfile
import com.example.stablestable.data.classes.UserProfile
import com.example.stablestable.data.repositories.impl.AccountServiceImpl
import com.example.stablestable.navigation.AuthViewModel
import kotlinx.coroutines.launch


class ProfileViewModel: ViewModel() {
    private val authViewModel: AuthViewModel = AuthViewModel()
    private val accountService: AccountServiceImpl = AccountServiceImpl()

    // Get the current logged in users ID as owner
    private val ownerId: String
        get() = authViewModel.userId ?: ""

    // This should be looked at if database change structure
    var fullName by mutableStateOf("")
    var phone by mutableStateOf("")
    var email by mutableStateOf("")

    // Save list of users current horses
    var horseList = mutableStateListOf<String>()
    // Save state for showing horse creation window
    var showHorseCreateWindow by mutableStateOf(false)

    private fun getCurrentUserProfile() {
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

    private fun getHorses() {
        viewModelScope.launch {
            try {
                val horses: List<HorseProfile?> = accountService.getHorsesByOwnerId(ownerId)
                horses.forEach { elem ->
                    if (elem != null) {
                        horseList.add(elem.name)
                    }
                }
            } catch (e: Exception){
                Log.d(TAG,"Get Horses Error: ${e.message.toString()}")
            }
        }
    }

    init {
        getCurrentUserProfile()
        getHorses()
    }
}
