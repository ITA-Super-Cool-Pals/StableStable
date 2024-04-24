package com.example.stablestable.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import com.example.stablestable.R
import com.example.stablestable.ui.Screen


@Composable
fun HomeUI(navController: NavController) {
    val viewModel = viewModel<HomeViewModel>()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 30.dp,
                top = 20.dp,
                end = 30.dp,
                bottom = 10.dp
            )

    ){
        Box(
            modifier = Modifier

        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = "Stable Name",
                    modifier = Modifier,
                    textAlign = TextAlign.Left,
                    fontSize = 35.sp
                )
                Image(
                    painter = painterResource(R.drawable.bell_01),
                    contentDescription = "Notification Bell",
                    modifier = Modifier
                        .size(30.dp) // Adjust the size as needed
                )
            }
        }
        Box(
            modifier = Modifier
        )
        {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 30.dp,
                        top = 200.dp,
                        end = 30.dp,
                        bottom = 100.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Column {
                    // Login Button
                    Button(
                        onClick = {
                            // TODO: Implement login logic
                            navController.navigate(route = Screen.MyProfileScreen.route)
                        },
                        modifier = Modifier
                            .width(130.dp)
                            .height(130.dp)
                            .padding(vertical = 15.dp), // Add padding between buttons
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Profile", fontSize = 10.sp)
                    }
                    Button(
                        onClick = {
                            // TODO: Implement login logic
                            /* navController.navigate(route = Screen.FodderPlanScreen.route)*/
                        },
                        modifier = Modifier
                            .width(130.dp)
                            .height(130.dp)
                            .padding(vertical = 15.dp), // Add padding between buttons
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Fodder Plan", fontSize = 10.sp)
                    }
                    Button(
                        onClick = {
                            // TODO: Implement login logic
                            /* navController.navigate(route = Screen.HorsesScreen.route)*/
                        },
                        modifier = Modifier
                            .width(130.dp)
                            .height(130.dp)
                            .padding(vertical = 15.dp), // Add padding between buttons
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Horses", fontSize = 10.sp)
                    }
                    Button(
                        onClick = {
                            // TODO: Implement login logic
                            /*navController.navigate(route = Screen.FaqScreen.route)*/
                        },
                        modifier = Modifier
                            .width(130.dp)
                            .height(130.dp)
                            .padding(vertical = 15.dp), // Add padding between buttons
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("FAQ", fontSize = 10.sp)
                    }
                }
                Column{
                    Button(
                        onClick = {
                            // TODO: Implement login logic
                            navController.navigate(route = Screen.CalendarScreen.route)
                        },
                        modifier = Modifier
                            .width(130.dp)
                            .height(130.dp)
                            .padding(vertical = 15.dp), // Add padding between buttons
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Calendar", fontSize = 10.sp)
                    }
                    Button(
                        onClick = {
                            // TODO: Implement login logic
                            /* navController.navigate(route = Screen.ShiftsScreen.route)*/
                        },
                        modifier = Modifier
                            .width(130.dp)
                            .height(130.dp)
                            .padding(vertical = 15.dp), // Add padding between buttons
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Shifts", fontSize = 10.sp)
                    }
                    Button(
                        onClick = {
                            // TODO: Implement login logic
                            /* navController.navigate(route = Screen.RidersScreen.route)*/
                        },
                        modifier = Modifier
                            .width(130.dp)
                            .height(130.dp)
                            .padding(vertical = 15.dp), // Add padding between buttons
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Riders", fontSize = 10.sp)
                    }
                    Button(
                        onClick = {
                            // TODO: Implement login logic
                            /* navController.navigate(route = Screen.BoardScreen.route)*/
                        },
                        modifier = Modifier
                            .width(130.dp)
                            .height(130.dp)
                            .padding(vertical = 15.dp), // Add padding between buttons
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Bulletin Board", fontSize = 10.sp)
                    }
                }
            }
        }
    }
/*
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
            value = viewModel.email,
            onValueChange = { viewModel.email = it },
            label = { Text("Username") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Password Field
        OutlinedTextField(
            value = viewModel.password,
            onValueChange = { viewModel.password = it },
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
                    // TODO: Implement login logic
                    val email = viewModel.email
                    val password = viewModel.password
                    accountService.userLogin(viewModel.email, viewModel.password) {
                        // Handle login success, navigate to next screen
                        navController.navigate(route = Screen.HomeScreen.route)
                    }
                    // Call a function to handle login with username and password
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
                    val username = viewModel.email
                    val password = viewModel.password
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
    }*/
}
