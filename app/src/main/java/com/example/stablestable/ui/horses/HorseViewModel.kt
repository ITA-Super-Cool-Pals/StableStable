package com.example.stablestable.ui.horses

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.sql.Date
import java.text.SimpleDateFormat

/*
 * Code by Emily
 */

@RequiresApi(Build.VERSION_CODES.O)
class HorseViewModel : ViewModel() {
    // Horse input field options
    var name by mutableStateOf("")

    // Sex selection dropdown
    val sexOptions = listOf("Female", "Male") // Options
    var selectedSex by mutableStateOf("") // Current sex selected
    var expandedSex by mutableStateOf(false) // Expanded check

    // Birth date
    var birthDateMillis by mutableLongStateOf(0L) // Milliseconds since epoch
    var birthDateFormatted by mutableStateOf("") // Human-readable format
    var showDateWindow by mutableStateOf(false)

    // Function to convert millis to date for human display
    @SuppressLint("SimpleDateFormat")
    fun convertMillisToDate(millis: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.format(Date(millis))
    }

    // Breed type
    val breedOptions = listOf("Arabian", "Caspian", "Icelandic")

}
