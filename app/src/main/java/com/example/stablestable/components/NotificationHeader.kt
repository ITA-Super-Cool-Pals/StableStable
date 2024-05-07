package com.example.stablestable.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.R
import com.example.stablestable.navigation.AuthViewModel
import com.example.stablestable.ui.home.HomeViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun CreateNotificationHeader(stableName: String, onLogout: () -> Unit) {
    val viewModel = viewModel<HomeViewModel>()
    val authViewModel = viewModel<AuthViewModel>()
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stableName,
            modifier = Modifier,
            textAlign = TextAlign.Left,
            fontSize = 35.sp
        )

        Button(onClick = {
            Firebase.auth.signOut()
            onLogout()
        }) {
            Text(text = "Logout")
        }

        Image(
            painter = painterResource(R.drawable.bell_01),
            contentDescription = "Notification Bell",
            modifier = Modifier
                .size(30.dp) // Adjust the size as needed
                .clickable {
                    viewModel.setNotificationDialogStateToTrue()
                }
        )
    }
}
