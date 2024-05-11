package com.example.stablestable.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.R

/*
 * Create User Window
 * Code by Emily
 */

@Composable
fun CreateUserWindow(
    onConfirm: (String, String, String, String, String) -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background, shape = RoundedCornerShape(10.dp))
                .padding(25.dp)
        ) {
            val viewModel = viewModel<LoginViewModel>()

            Column {
                Text(
                    stringResource(R.string.userCreation),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )

                // Email field
                OutlinedTextField(
                    value = viewModel.email,
                    onValueChange = { viewModel.email = it },
                    label = { Text(stringResource(R.string.email)) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
                )

                // Password field
                OutlinedTextField(
                    value = viewModel.password,
                    onValueChange = { viewModel.password = it },
                    label = { Text(stringResource(R.string.password)) },
                    visualTransformation = PasswordVisualTransformation(), // Hide password text
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
                )

                // First name
                OutlinedTextField(
                    value = viewModel.firstName,
                    onValueChange = { viewModel.firstName = it },
                    label = { Text(stringResource(R.string.firstName)) }
                )
                // Last name
                OutlinedTextField(
                    value = viewModel.lastName,
                    onValueChange = { viewModel.lastName = it },
                    label = { Text(stringResource(R.string.lastName)) }
                )

                // Phone Field
                OutlinedTextField(
                    value = viewModel.phone,
                    onValueChange = {
                        if (it.all { char -> char.isDigit() }) {
                            viewModel.phone = it
                        }
                    },
                    label = { Text(stringResource(R.string.phone)) },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        onConfirm(
                            viewModel.email,
                            viewModel.password,
                            viewModel.firstName,
                            viewModel.lastName,
                            viewModel.phone
                        )
                    },
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(stringResource(R.string.createUser))
                }

                if (viewModel.createUserErrorMessage != "") {
                    Text(
                        viewModel.createUserErrorMessage,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}
