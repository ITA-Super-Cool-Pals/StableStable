package com.example.stablestable.components.datePicker

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.sql.Date
import java.text.SimpleDateFormat

/*
 * Code by Emily
 */

class DatePickerViewModel : ViewModel() {
    // Track if a date has been selected
    var dateSelected by mutableStateOf(false)

    // Function to convert millis to date string
    @SuppressLint("SimpleDateFormat")
    fun convertMillisToDate(millis: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.format(Date(millis))
    }
}
