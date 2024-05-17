package com.example.stablestable.ui.horses

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stablestable.data.classes.HorseProfile
import com.example.stablestable.data.classes.UserProfile
import com.example.stablestable.data.repositories.impl.AccountServiceImpl
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.Period
import java.time.ZoneId

/*
 * Viewmodel to handle displaying a horses information
 * Code by Emily
 */

@RequiresApi(Build.VERSION_CODES.O)
class HorseProfileViewModel: ViewModel() {
    private val accountService: AccountServiceImpl = AccountServiceImpl()

    var horseProfile = mutableStateOf(HorseProfile())
    var ownerProfile = mutableStateOf(UserProfile())

    var ageYears by mutableIntStateOf(0)
    var ageMonths by mutableIntStateOf(0)

    var isOwner by mutableStateOf(false)


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

                // Convert the horses age from millis to "x years y months" format
                getHorseAge(horseProfile.value.age)

                // Check if the current user id matches owner id
                val userId = Firebase.auth.currentUser?.uid
                if (userId == horseProfile.value.ownerId) {
                    isOwner = true
                    println("you are owner")
                }
            } catch (e: Exception) {
                Log.d(TAG, "Get Horse Error: ${e.message.toString()}")
            }
        }
    }

    // Convert the horses age from millis to "x years y months" format
    private fun getHorseAge(age: Timestamp) {
        val birthDateMillis = age.seconds * 1000

        val birthDateLocal = Instant.ofEpochMilli(birthDateMillis).atZone(ZoneId.systemDefault()).toLocalDate()
        val currentDate = LocalDate.now()
        val period = Period.between(birthDateLocal, currentDate)

        ageYears = period.years
        ageMonths = period.months
    }
}
