package com.example.stablestable.ui.horses

import android.content.ContentValues.TAG
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    val ownerId: String
        get() = authViewModel.userId ?: ""

    // Get the current logged in users Stable ID
    val stableId: String
        get() = authViewModel.currentUserProfile.value?.stableId ?: ""

    fun createHorseProfile(): HorseProfile {
        return HorseProfile(
            ownerId = ownerId,
            stableId = stableId,
            name = name,
            breed = breed,
            sex = selectedSex,
            age = birthDateMillis.toString()
        )
    }

    fun addHorseToFirebase() {
        val horseProfile = createHorseProfile()
        viewModelScope.launch {
            try {
                accountService.addHorse(horseProfile)
            } catch (e: Exception) {
                Log.d(TAG, "Add Horse to database failed: ${e.message}")
            }
        }
    }


}
