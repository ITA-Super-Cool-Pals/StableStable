package com.example.stablestable.ui.horses

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stablestable.data.classes.HorseProfile
import com.example.stablestable.data.classes.UserProfile
import com.example.stablestable.data.repositories.impl.AccountServiceImpl
import com.example.stablestable.navigation.AuthViewModel
import kotlinx.coroutines.launch

/*
 * Code by Emily
 */

@RequiresApi(Build.VERSION_CODES.O)
class HorseProfileViewModel: ViewModel() {
    private val accountService: AccountServiceImpl = AccountServiceImpl()

    var horseProfile = mutableStateOf(HorseProfile())
    var ownerProfile = mutableStateOf(UserProfile())

    fun getHorse(horseId: String) {
        viewModelScope.launch {
            try {
                val horse: HorseProfile? = accountService.getHorseById(horseId)
                Log.d(TAG, "Fetched horse: $horse")
                horseProfile.value = horse ?: HorseProfile()

                // Fetch the owner's profile
                val owner: UserProfile? = accountService.getUserById(horseProfile.value.ownerId)
                Log.d(TAG, "Fetched owner: $owner")
                ownerProfile.value = owner ?: UserProfile()
            } catch (e: Exception) {
                Log.d(TAG, "Get Horse Error: ${e.message.toString()}")
            }
        }
    }
}
