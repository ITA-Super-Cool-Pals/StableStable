package com.example.stablestable.ui.profile

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stablestable.data.classes.HorseItem
import com.example.stablestable.data.classes.HorseProfile
import com.example.stablestable.data.repositories.impl.AccountServiceImpl
import com.example.stablestable.navigation.AuthViewModel
import kotlinx.coroutines.launch


/*
    Filen er skrevet af Emily og Josef

 */

class UserProfileViewModel : ViewModel() {
    private val authViewModel: AuthViewModel = AuthViewModel()
    private val accountService: AccountServiceImpl = AccountServiceImpl()

    // This should be looked at if database change structure
    var fullName by mutableStateOf("")
    var phone by mutableStateOf("")
    var email by mutableStateOf("")

    // Save list of users current horses
    var horseList = mutableStateListOf<HorseItem>()

    // Save state for showing horse add button
    var showAddHorseButton by mutableStateOf(false)

    // Save state for showing horse creation window
    var showHorseCreateWindow by mutableStateOf(false)

    // Load the user profile
    fun loadUserProfile(userId: String) {
        viewModelScope.launch {
            try {
                // Check if the current authenticated user matches the user ID
                // If yes, fetch from locally stored data from authViewModel
                if (userId == authViewModel.userId) {
                    authViewModel.currentUserProfile.collect { currentUser ->
                        if (currentUser != null) {
                            fullName = "${currentUser.firstName} ${currentUser.lastName}"
                            phone = currentUser.phone
                            email = currentUser.email
                            // Add the add horse button to the screen
                            showAddHorseButton = true
                        }
                    }
                } else {
                    // Fetch from firestore database if user ID don't match the current authenticated user
                    val userProfile = accountService.getUserById(userId)
                    if (userProfile != null) {
                        fullName = "${userProfile.firstName} ${userProfile.lastName}"
                        phone = userProfile.phone
                        email = userProfile.email
                        // Ensure the add horse button is not visible
                        showAddHorseButton = false
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, "Error loading user profile: ${e.message.toString()}")

            }
        }
    }

    // List all horses based on user ID
    fun getHorses(userId: String) {
        viewModelScope.launch {
            try {
                val horses: List<Pair<String, HorseProfile?>> =
                    accountService.getHorsesByOwnerId(userId)
                horseList.clear()
                horses.forEach { (horseId, horseProfile) ->
                    if (horseProfile != null) {
                        horseList.add(HorseItem(horseId, horseProfile.name))
                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, "Get Horses Error: ${e.message.toString()}")
            }
        }
    }
}
