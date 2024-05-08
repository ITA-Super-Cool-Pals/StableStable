package com.example.stablestable.ui.horses

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.R

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HorseCreateScreen() {
    val viewModel = viewModel<HorseViewModel>()

    Box {

        Column {
            // Title information
            Text(
                stringResource(R.string.addHorse),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center
            )

            // Name field
            OutlinedTextField(
                value = viewModel.name,
                onValueChange = { viewModel.name = it },
                label = { Text(stringResource(R.string.name)) },
            )

            // Sex option dropdown
            ExposedDropdownMenuBox(
                expanded = viewModel.expandedSex,
                onExpandedChange = {
                    viewModel.expandedSex = !viewModel.expandedSex
                }
            ) {
                OutlinedTextField(
                    value = viewModel.selectedSex,
                    onValueChange = { },
                    readOnly = true,
                    label = { Text(stringResource(R.string.sex)) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = viewModel.expandedSex
                        )
                    },
                    modifier = Modifier.menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = viewModel.expandedSex,
                    onDismissRequest = {
                        viewModel.expandedSex
                    }
                ) {
                    viewModel.sexOptions.forEach { sex ->
                        DropdownMenuItem(
                            text = { Text(sex) },
                            onClick = {
                                viewModel.selectedSex = sex
                                viewModel.expandedSex = false
                            }
                        )
                    }
                }
            }

            // Age dropdown
            // .clickable modifier on OutlinedTextField didn't work properly
            // ExposedDropdownMenuBox let me get around
            ExposedDropdownMenuBox(
                expanded = viewModel.showDateWindow,
                onExpandedChange = {
                    viewModel.showDateWindow = !viewModel.showDateWindow
                }
            ) {
                OutlinedTextField(
                    value = viewModel.birthDate,
                    onValueChange = { },
                    label = { Text(stringResource(R.string.birthDate)) },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = viewModel.showDateWindow)
                    },
                    readOnly = true,
                    modifier = Modifier.menuAnchor()
                )
            }
            if (viewModel.showDateWindow) {
                ShowDate()
            }

            // Breed
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDate() {
    val dateState = rememberDatePickerState()
    DatePicker(
        state = dateState
    )
}
