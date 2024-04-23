package com.example.stablestable.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginUI(
    onRegistrationSuccess: () -> Unit,
    onRegistrationFailure: () -> Unit
) {
    val loginViewModel = viewModel<LoginViewModel>()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // StableStable Logo Text Header
        Text(
            text = "StableStable",
            modifier = Modifier
                .padding(bottom = 25.dp),
            textAlign = TextAlign.Center,
            fontSize = 42.sp
        )

        // Username Field
        OutlinedTextField(
            value = loginViewModel.email,
            onValueChange = { loginViewModel.email = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Password Field
        OutlinedTextField(
            value = loginViewModel.password,
            onValueChange = { loginViewModel.password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(), // Hide password text
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Login / Create User Buttons
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Login Button
            Button(
                onClick = {
                    loginViewModel.userLogin(onRegistrationSuccess, onRegistrationFailure)
                    // TODO: Finish proper error display
                },
                modifier = Modifier
                    .weight(1f) // Assign equal weight to both buttons to ensure same size
                    .padding(end = 6.dp), // Add padding between buttons
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Login", fontSize = 16.sp)
            }

            // Create User Button
            Button(
                onClick = {
                    // TODO: Implement create user logic
                    // Call a function to move to create user screen
                },
                modifier = Modifier
                    .weight(1f) // Assign equal weight to both buttons to ensure same size
                    .padding(start = 6.dp), // Add padding between buttons
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Create User", fontSize = 16.sp)
            }
        }
    }
}
