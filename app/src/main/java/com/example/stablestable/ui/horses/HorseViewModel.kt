package com.example.stablestable.ui.horses

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.time.LocalDate
import java.time.Month

@RequiresApi(Build.VERSION_CODES.O)
class HorseViewModel : ViewModel() {
    // Horse input field options
    var name by mutableStateOf("")

    // Sex selection dropdown
    val sexOptions = listOf("Female", "Male") // Options
    var selectedSex by mutableStateOf("") // Current sex selected
    var expandedSex by mutableStateOf(false) // Expanded check

    // Birth date
    var birthDate by mutableStateOf("")
    var showDateWindow by mutableStateOf(false)

    // Breed type
    var breed by mutableStateOf("")

}
