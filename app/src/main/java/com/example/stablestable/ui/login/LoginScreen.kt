package com.example.stablestable.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen(
    onRegistrationSuccess: () -> Unit,
    onRegistrationFailure: () -> Unit
) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LoginUI(
            onRegistrationSuccess = onRegistrationSuccess,
            onRegistrationFailure = onRegistrationFailure
        )
    }
}
