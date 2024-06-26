package com.example.stablestable.ui.horses

import android.app.Application
import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.stablestable.R
import com.example.stablestable.data.classes.HorseProfile
import com.example.stablestable.data.repositories.impl.AccountServiceImpl
import com.example.stablestable.navigation.AuthViewModel
import com.google.firebase.Timestamp
import kotlinx.coroutines.launch

/*
 * Viewmodel to handle adding a horse to the database
 * Code by Emily
 */

@RequiresApi(Build.VERSION_CODES.O)
class HorseCreateViewModel(application: Application): AndroidViewModel(application) {
    private val authViewModel: AuthViewModel = AuthViewModel()
    private val accountService: AccountServiceImpl = AccountServiceImpl()

    var errorMessage by mutableStateOf("")

    // Horse name input field
    var name by mutableStateOf("")

    // Breed type input field
    var breed by mutableStateOf("")

    // Sex selection dropdown
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

    // Create a new horse profile object
    private fun createHorseProfile(): HorseProfile {
        return HorseProfile(
            ownerId = ownerId,
            stableId = stableId,
            name = name,
            breed = breed,
            sex = selectedSex,
            age = Timestamp(birthDateMillis / 1000, 0)
        )
    }

    // Add a horse to the database
    fun addHorseToFirebase(onConfirm: () -> Unit) {
        // Check if all fields are filled
        if (name.isEmpty() || breed.isEmpty() || selectedSex.isEmpty() || birthDateMillis == 0L) {
            errorMessage = getApplication<Application>().getString(R.string.fillAllFields)
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

    // Clear all the input fields
    private fun clearFields() {
        name = ""
        breed = ""
        selectedSex = ""
        birthDateMillis = 0L
        birthDateFormatted = ""
    }
}
