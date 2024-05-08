package com.example.stablestable.components.datePicker

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

/*
 * Function to show calendar for date selection
 *
 * dateMillis: Date in milliseconds since epoch
 * dateFormatted: Date in human readable string (dd/MM/yyyy)
 * onDateSelected: Function to pass variables
 *
 * Use example:
 *   ShowDatePicker(
 *       dateMillis = viewModel.birthDateMillis,
 *       dateFormatted = viewModel.birthDateFormatted,
 *   ) { millis, formattedDate ->
 *       viewModel.birthDateMillis = millis
 *       viewModel.birthDateFormatted = formattedDate
 *   }
 *
 * Code by Emily
 */

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDatePicker(
    dateMillis: Long,
    dateFormatted: String,
    onDateSelected: (Long, String) -> Unit
) {
    val viewModel = viewModel<DatePickerViewModel>()

    val dateState = rememberDatePickerState()

    dateState.selectedDateMillis?.let { millis ->
        val formattedDate = viewModel.convertMillisToDate(millis)
        onDateSelected(millis, formattedDate)
    }

    DatePicker(
        state = dateState
    )
}
