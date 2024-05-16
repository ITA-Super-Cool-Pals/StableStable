package com.example.stablestable.components.datePicker

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.R

/*
 * Function to show calendar for date selection
 *
 * millis: Date in milliseconds since epoch
 * formattedDate: Date in human readable string (dd/MM/yyyy)
 *
 *   Arguments
 * showDialog: Boolean, controls whether dialog is shown or not
 * onDialogDismiss: Function to close dialog window
 * onDateSelected: Function to pass variables
 * title (Optional): Small title above the calendar. Defaults to "Select Date"
 * headline (Optional): Text before a date has been selected. Defaults to "Select Date"
 *
 * Use example:
 *   ShowDatePicker(
 *       showDialog = viewModel.showDateWindow,
 *       onDialogDismiss = { viewModel.showDateWindow },
 *       onDateSelected = { millis, formattedDate ->
 *           viewModel.birthDateMillis = millis
 *           viewModel.birthDateFormatted = formattedDate
 *       },
 *       headline = stringResource(R.string.birthDateWhen)
 *   )
 *
 * Code by Emily
 */

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDatePicker(
    showDialog: Boolean,
    onDialogDismiss: () -> Unit,
    onDateSelected: (Long, String) -> Unit,
    title: String = stringResource(R.string.selectDate),
    headline: String = stringResource(R.string.selectDate),
) {
    val viewModel = viewModel<DatePickerViewModel>()

    val dateState = rememberDatePickerState()

    dateState.selectedDateMillis?.let { millis ->
        val formattedDate = viewModel.convertMillisToDate(millis)
        onDateSelected(millis, formattedDate)
        viewModel.dateSelected = true
    }

    /*
     * Track if date has been selected
     * If yes, update headline to show date selected
     * If no, keep headline as default / user argument
     */
    val headlineUpdate = if (viewModel.dateSelected) {
        viewModel.convertMillisToDate(dateState.selectedDateMillis ?: 0)
    } else {
        headline
    }

    if (showDialog) {
        DatePickerDialog(
            onDismissRequest = onDialogDismiss,
            confirmButton = {
                Button(
                    onClick = onDialogDismiss,
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(text = "OK")
                }
            },
            shape = RoundedCornerShape(10.dp)
        ) {
            DatePicker(
                state = dateState,
                title = {
                    Text(
                        title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        textAlign = TextAlign.Center
                    )
                },
                headline = {
                    Text(
                        headlineUpdate,
                        modifier = Modifier
                            .padding(start = 20.dp)
                    )
                }
            )
        }
    }
}
