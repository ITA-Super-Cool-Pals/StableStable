package com.example.stablestable.components.datePicker

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import java.sql.Date
import java.text.SimpleDateFormat

/*
 * Code by Emily
 */

class DatePickerViewModel : ViewModel() {
    // Function to convert millis to date string
    @SuppressLint("SimpleDateFormat")
    fun convertMillisToDate(millis: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.format(Date(millis))
    }
}
