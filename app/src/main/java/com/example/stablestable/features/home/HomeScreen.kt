package com.example.stablestable.features.home

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton
import androidx.compose.ui.res.stringResource
import com.example.stablestable.R
import com.example.stablestable.features.Screen


@Composable
fun HomeScreen(navController: NavController) {
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
                    text = "Stald Navn",
                    modifier = Modifier,
                    textAlign = TextAlign.Left,
                    fontSize = 35.sp
                )
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
        Box(
            modifier = Modifier
        )
        {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 15.dp,
                        top = 200.dp,
                        end = 15.dp,
                        bottom = 20.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween
            )
            {
                Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                    // Login Button
                    Button(
                        onClick = {
                            // TODO: Implement login logic
                            navController.navigate(route = Screen.MyProfileScreen.route)
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .padding(vertical = 15.dp), // Add padding between buttons
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.profil),
                            fontSize = 10.sp)
                    }
                    Button(
                        enabled = false,
                        onClick = {
                            // TODO: Implement login logic
                            /* navController.navigate(route = Screen.FodderPlanScreen.route)*/
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .padding(vertical = 15.dp), // Add padding between buttons
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.foderplan),
                            fontSize = 10.sp)
                    }
                    Button(
                        enabled = false,
                        onClick = {
                            // TODO: Implement login logic
                            /* navController.navigate(route = Screen.HorsesScreen.route)*/
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .padding(vertical = 15.dp), // Add padding between buttons
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.kalender),
                            fontSize = 10.sp)
                    }
                    Button(
                        enabled = false,
                        onClick = {
                            // TODO: Implement login logic
                            /*navController.navigate(route = Screen.FaqScreen.route)*/
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .padding(vertical = 15.dp), // Add padding between buttons
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.faq),
                            fontSize = 10.sp)
                    }
                }
                Column{
                    Button(
                        enabled = false,
                        onClick = {
                            // TODO: Implement login logic
                            navController.navigate(route = Screen.CalendarScreen.route)
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .padding(vertical = 15.dp), // Add padding between buttons
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.vagtplan),
                            fontSize = 10.sp)
                    }
                    Button(
                        onClick = {
                            navController.navigate(route = Screen.StableUsers.route)
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .padding(vertical = 15.dp), // Add padding between buttons
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.ryttere),
                            fontSize = 10.sp)
                    }
                    Button(
                        enabled = false,
                        onClick = {
                            // TODO: Implement login logic
                            /* navController.navigate(route = Screen.RidersScreen.route)*/
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .padding(vertical = 15.dp), // Add padding between buttons
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.heste),
                            fontSize = 10.sp)
                    }
                    Button(
                        enabled = false,
                        onClick = {
                            // TODO: Implement login logic
                            /* navController.navigate(route = Screen.BoardScreen.route)*/
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                            .padding(vertical = 15.dp), // Add padding between buttons
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Bulletin Board", fontSize = 10.sp)
                    }
                }
            }
        }
    }
    if (viewModel.showNotificationDialog) {
        NotificationsDialog()
    }
}
@Composable
private fun NotificationsDialog(
    //listOfNotifications: List<String>,
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel<HomeViewModel>()

    AlertDialog(
        onDismissRequest = {
            viewModel.setNotificationDialogStateToFalse()
        },
        title = {
            Text(
                text = stringResource(R.string.notifikationer)
            ) },
        text = {
               Text(
                   text = "Test-notifikation 01"
               ) },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    viewModel.setNotificationDialogStateToFalse()
                }
            ) {
                Text(
                    text = stringResource(R.string.luk)
                )
            }
        },
        confirmButton = {

        }
    )
}
