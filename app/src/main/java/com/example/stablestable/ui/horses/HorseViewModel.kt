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
import com.example.stablestable.data.repositories.impl.AccountServiceImpl
import com.example.stablestable.navigation.AuthViewModel
import kotlinx.coroutines.launch

/*
 * Code by Emily
 */

@RequiresApi(Build.VERSION_CODES.O)
class HorseViewModel: ViewModel() {
    private val authViewModel: AuthViewModel = AuthViewModel()
    private val accountService: AccountServiceImpl = AccountServiceImpl()

    var errorMessage by mutableStateOf("")

    // Horse name input field
    var name by mutableStateOf("")

    // Breed type input field
    var breed by mutableStateOf("")

    // Sex selection dropdown
    val sexOptions = listOf("Female", "Male") // Options
    var selectedSex by mutableStateOf("") // Current sex selected
    var expandedSex by mutableStateOf(false) // Expanded check

    // Birth date dialog window
    var birthDateMillis by mutableLongStateOf(0L) // Milliseconds since epoch
    var birthDateFormatted by mutableStateOf("") // Human-readable format
    var showDateWindow by mutableStateOf(false)

    // Get the current logged in users ID as owner
    private val ownerId: String
        get() = authViewModel.userId ?: ""

    // Get the current logged in users Stable ID
    private val stableId: String
        get() = authViewModel.currentUserProfile.value?.stableId ?: ""

    private fun createHorseProfile(): HorseProfile {
        return HorseProfile(
            ownerId = ownerId,
            stableId = stableId,
            name = name,
            breed = breed,
            sex = selectedSex,
            age = birthDateMillis.toString()
        )
    }

    fun addHorseToFirebase(onConfirm: () -> Unit) {
        // Check if all fields are filled
        if (name.isEmpty() || breed.isEmpty() || selectedSex.isEmpty() || birthDateMillis == 0L) {
            errorMessage = "Please fill in all fields"
            return
        }

        val horseProfile = createHorseProfile()
        viewModelScope.launch {
            try {
                accountService.addHorse(horseProfile)
                clearFields()
                onConfirm()

            } catch (e: Exception) {
                Log.d(TAG, "Add Horse to database failed: ${e.message}")
            }
        }
    }

    private fun clearFields() {
        name = ""
        breed = ""
        selectedSex = ""
        birthDateMillis = 0L
        birthDateFormatted = ""
    }

    var horseProfile = mutableStateOf(HorseProfile())

    fun getHorse(horseId: String) {
        viewModelScope.launch {
            try {
                val horse: HorseProfile? = accountService.getHorseById(horseId)
                Log.d(TAG, "Fetched horse: $horse")
                horseProfile.value = horse ?: HorseProfile()
            } catch (e: Exception) {
                Log.d(TAG, "Get Horse Error: ${e.message.toString()}")
            }
        }
    }


}
