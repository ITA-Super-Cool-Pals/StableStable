package com.example.stablestable.ui.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stablestable.ui.Screen

@Composable
fun LoginScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // StableStable Logo Text Header
        Text(
            text = "StableStable",
            modifier = Modifier
                .padding(top = 250.dp, bottom = 50.dp),
            textAlign = TextAlign.Center,
            fontSize = 32.sp)

        /*
         * Login Field
         */
        // Username
        LoginField("Brugernavn")
        // Password
        LoginField("Kodeord")

        /*
         * Login / Create User Buttons
         */
        Row(
            modifier = Modifier
                .fillMaxWidth(0.68f),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            AddButton(text = "Login", onButtonClick = {
                navController.navigate(route = Screen.HomeScreen.route)
            })
            AddButton(text = "Opret Bruger", onButtonClick = {
                navController.navigate(route = Screen.HomeScreen.route)
            })
        }

    }
}

/*
 * Button Function
 * Text = Text to display on button
 */
@Composable
private fun AddButton(text: String, onButtonClick: () -> Unit) {
    Button(
        onClick = onButtonClick,
        modifier = Modifier
            .width(130.dp)
    ) {
        Text(text = text, fontWeight = FontWeight.Bold)
    }
}
