package com.example.stablestable.ui.horses

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.R
import com.example.stablestable.components.datePicker.ShowDatePicker

/*
 * Horse creation/add screen
 * Code by Emily
 */

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HorseCreateScreen(
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        val viewModel: HorseCreateViewModel = viewModel()

        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(10.dp))
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 30.dp)
            ) {
                // Title information
                Text(
                    stringResource(R.string.addHorse),
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.padding(10.dp))

                // Name field
                OutlinedTextField(
                    value = viewModel.name,
                    onValueChange = { viewModel.name = it },
                    label = { Text(stringResource(R.string.name)) },
                )

                Spacer(modifier = Modifier.padding(5.dp))

                // Breed
                OutlinedTextField(
                    value = viewModel.breed,
                    onValueChange = { viewModel.breed = it },
                    label = { Text(stringResource(R.string.breed)) },
                )

                Spacer(modifier = Modifier.padding(5.dp))

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
                        // Sex options - set here instead of viewmodel so stringResource can be used
                        val sexOptions = listOf(
                            stringResource(R.string.mare),
                            stringResource(R.string.stallion),
                            stringResource(R.string.gelding))
                        sexOptions.forEach { sex ->
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

                Spacer(modifier = Modifier.padding(5.dp))

                // Age picker
                // .clickable modifier on OutlinedTextField didn't work properly
                // (clickable portion is *behind* text field, amazing..)
                // ExposedDropdownMenuBox lets me get around it without an extra button
                ExposedDropdownMenuBox(
                    expanded = viewModel.showDateWindow,
                    onExpandedChange = {
                        viewModel.showDateWindow = !viewModel.showDateWindow
                    }
                ) {
                    OutlinedTextField(
                        value = viewModel.birthDateFormatted,
                        onValueChange = { },
                        readOnly = true,
                        label = { Text(stringResource(R.string.birthDate)) },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = stringResource(R.string.selectDate)
                            )
                        },
                        modifier = Modifier.menuAnchor()
                    )
                }
                ShowDatePicker(
                    showDialog = viewModel.showDateWindow,
                    onDialogDismiss = { viewModel.showDateWindow = false },
                    onDateSelected = { millis, formattedDate ->
                        viewModel.birthDateMillis = millis
                        viewModel.birthDateFormatted = formattedDate
                    },
                    headline = stringResource(R.string.birthDateWhen)
                )

                Spacer(modifier = Modifier.padding(5.dp))

                // Show error message if not all fields are filled
                Text(
                    text = viewModel.errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier
                        .padding(bottom = 5.dp)
                )

                // Button row
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 50.dp)
                ) {
                    Button(
                        onClick = { viewModel.addHorseToFirebase { onConfirm() } },
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(stringResource(R.string.confirm))
                    }
                    Button(
                        onClick = { onDismiss() },
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text(stringResource(R.string.cancel))
                    }
                }

                Spacer(modifier = Modifier.padding(10.dp))
            }
        }
    }
}

