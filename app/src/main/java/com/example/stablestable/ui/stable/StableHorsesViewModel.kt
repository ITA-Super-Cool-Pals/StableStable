package com.example.stablestable.ui.stable

import android.content.ContentValues
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stablestable.data.classes.HorseItem
import com.example.stablestable.data.classes.HorseProfile
import com.example.stablestable.data.repositories.impl.AccountServiceImpl
import com.example.stablestable.navigation.AuthViewModel
import kotlinx.coroutines.launch

/*
 * Viewmodel to handle displaying all horses in a stable
 * Code by Emily
 */

class StableHorsesViewModel: ViewModel() {
    private val authViewModel: AuthViewModel = AuthViewModel()
    private val accountService: AccountServiceImpl = AccountServiceImpl()
    // Get current users Stable ID
    private var stableId: String? = null

    // Save list of users current horses
    var horseList = mutableStateListOf<HorseItem>()

    private fun getHorses() {
        viewModelScope.launch {
            try {
                val horses: List<Pair<String, HorseProfile?>> = accountService.getHorsesByStableId(stableId!!)
                println("Horses: $horses")
                println("stableid: $stableId")
                horseList.clear()
                horses.forEach { (horseId, horseProfile) ->
                    if (horseProfile != null) {
                        horseList.add(HorseItem(horseId, horseProfile.name))
                    }
                }
            } catch (e: Exception){
                Log.d(ContentValues.TAG,"Get Horses Error: ${e.message.toString()}")
            }
        }
    }

    init {
        viewModelScope.launch {
            authViewModel.currentUserProfile.collect { currentUser ->
                if (currentUser != null) {
                    stableId = currentUser.stableId
                    getHorses()
                }
            }
        }
    }

}
