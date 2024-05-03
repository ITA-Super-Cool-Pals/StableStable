package com.example.stablestable.components

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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.ui.login.LoginViewModel

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
                .background(Color.White, shape = RoundedCornerShape(10.dp))
                .padding(25.dp)
        ) {
            val loginViewModel = viewModel<LoginViewModel>()

            Column {
                Text(
                    "Bruger Registrering",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )

                // Email field
                OutlinedTextField(
                    value = loginViewModel.email,
                    onValueChange = { loginViewModel.email = it },
                    label = { Text("Email") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
                )

                // Password field
                OutlinedTextField(
                    value = loginViewModel.password,
                    onValueChange = { loginViewModel.password = it },
                    label = { Text("Kodeord") },
                    visualTransformation = PasswordVisualTransformation(), // Hide password text
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
                )

                // First name
                OutlinedTextField(
                    value = loginViewModel.firstName,
                    onValueChange = { loginViewModel.firstName = it },
                    label = { Text("Fornavn") }
                )
                // Last name
                OutlinedTextField(
                    value = loginViewModel.lastName,
                    onValueChange = { loginViewModel.lastName = it },
                    label = { Text("Efternavn") }
                )

                // Phone Field
                OutlinedTextField(
                    value = loginViewModel.phone,
                    onValueChange = {
                        if (it.all { char -> char.isDigit() }) {
                            loginViewModel.phone = it
                        }
                    },
                    label = { Text("Telefon") },
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        onConfirm(
                            loginViewModel.email,
                            loginViewModel.password,
                            loginViewModel.firstName,
                            loginViewModel.lastName,
                            loginViewModel.phone
                        )
                    },
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Opret Bruger")
                }

                if (loginViewModel.createUserErrorMessage != "") {
                    Text(
                        loginViewModel.createUserErrorMessage,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Red,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}
