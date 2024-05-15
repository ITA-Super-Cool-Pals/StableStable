package com.example.stablestable.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.R

/*
 * Login Screen
 * Code by Emily
 */

@Composable
fun LoginScreen(
    onRegistrationSuccess: () -> Unit
) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val viewModel = viewModel<LoginViewModel>()

        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // StableStable Logo Text Header
            Text(
                text = stringResource(R.string.appName),
                modifier = Modifier
                    .padding(bottom = 25.dp),
                textAlign = TextAlign.Center,
                fontSize = 42.sp
            )

            // Username Field
            OutlinedTextField(
                value = viewModel.email,
                onValueChange = { viewModel.email = it },
                label = { Text(stringResource(R.string.email)) },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = stringResource(R.string.email)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
            )

            // Password Field
            OutlinedTextField(
                value = viewModel.password,
                onValueChange = { viewModel.password = it },
                label = { Text(stringResource(R.string.password)) },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Password,
                        contentDescription = stringResource(R.string.password)
                    )
                },
                visualTransformation = PasswordVisualTransformation(), // Hide password text
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password)
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
                        viewModel.userLogin(
                            onRegistrationSuccess
                        )
                    },
                    modifier = Modifier
                        .weight(1f), // Assign equal weight to both buttons to ensure same size
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(text = stringResource(R.string.login), fontSize = 16.sp)
                }

                Spacer(modifier = Modifier.width(30.dp))

                // Create User Button
                Button(
                    onClick = {
                        viewModel.showCreateUserWindow = true
                    },
                    modifier = Modifier
                        .weight(1f), // Assign equal weight to both buttons to ensure same size
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(stringResource(R.string.createUser), fontSize = 16.sp)
                }
            }

            if (viewModel.loginErrorMessage != "") {
                Text(
                    viewModel.loginErrorMessage,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }

        // Display CreateUserWindow dialog if showCreateUserWindow is true
        if (viewModel.showCreateUserWindow) {
            CreateUserWindow(
                onConfirm = { email, password, firstName, lastName, phone ->
                    // Handle user creation here with additional information (fullname, phone)
                    viewModel.firstName = firstName
                    viewModel.lastName = lastName
                    viewModel.phone = phone
                    viewModel.email = email
                    viewModel.password = password
                    viewModel.userCreate(
                        navigateOnSuccess = {
                            onRegistrationSuccess()
                            viewModel.showCreateUserWindow = false
                        }
                    )
                },
                onDismiss = {
                    viewModel.showCreateUserWindow = false // Dismiss window if dismissed
                }
            )
        }
    }
}
