package com.example.stablestable.ui.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.R
import com.example.stablestable.ui.horses.HorseCreateScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserProfileScreenContent(
    userId: String,
    onHorseClick: (String) -> Unit,
    paddingValues: PaddingValues
){

    val userProfileViewModel: UserProfileViewModel = viewModel()

    LaunchedEffect(userId) {
        userProfileViewModel.loadUserProfile(userId)
        userProfileViewModel.getHorses(userId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ){
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            // Profile picture and name
            Row {
                // Profile picture - default to an icon if no profile image is available
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile Picture",
                    modifier = Modifier.size(100.dp)
                )

                Spacer(modifier = Modifier.width(width = 16.dp))

                // Name
                Text(
                    text = userProfileViewModel.fullName,
                    fontSize = 26.sp,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Phone and Email
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Red, RoundedCornerShape(20.dp, 20.dp, 10.dp, 10.dp))
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Color.LightGray, // TODO: Change to material colorscheme
                                shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                            )
                    ) {
                        Text(
                            text = stringResource(R.string.contactInfo),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .padding(start = 10.dp)
                    ) {
                        // Phone
                        Text(text = stringResource(R.string.phone), color = Color.Gray)
                        Row {
                            Icon(
                                imageVector = Icons.Default.Phone,
                                contentDescription = "Phone",
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(end = 5.dp)
                                    .align(Alignment.CenterVertically)
                            )
                            Text(text = userProfileViewModel.phone, fontSize = 22.sp)
                        }


                        Spacer(modifier = Modifier.height(20.dp))

                        // Email
                        Text(text = stringResource(R.string.email), color = Color.Gray)
                        Row {
                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Phone",
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(end = 5.dp)
                                    .align(Alignment.CenterVertically)
                            )
                            Text(text = userProfileViewModel.email, fontSize = 22.sp)
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // My horses section
            Box(
                modifier = Modifier
                    .border(1.dp, Color.Red, RoundedCornerShape(20.dp, 20.dp, 10.dp, 10.dp))
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
            ) {
                Column {
                    // Header section
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                Color.LightGray, // TODO: Change to material colorscheme
                                shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                            )
                    ) {
                        Text(
                            text = stringResource(R.string.myHorses),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 5.dp)
                        )
                    }

                    // My horses list
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        userProfileViewModel.horseList.forEach { horseItem ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .clickable { onHorseClick(horseItem.horseId) }
                                    .fillMaxWidth()
                                    .padding(start = 20.dp, end = 20.dp)
                            ) {
                                Text(
                                    text = horseItem.horseName,
                                    fontSize = 30.sp
                                )
                                Icon(
                                    imageVector = Icons.Default.Visibility,
                                    contentDescription = "View Horse Profile"
                                )
                            }
                            // Underline
                            Spacer(
                                modifier = Modifier
                                    .fillMaxWidth(0.9f)
                                    .height(1.dp)
                                    .background(Color.Gray)
                            )
                        }
                    }

                    /*
                     * Add Horse Button
                     * Check if the current authenticated user matches the user profile being viewed
                     * If match, show the add horse button, if not don't show the button
                     */
                    if (userProfileViewModel.showAddHorseButton) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp)
                        ) {
                            Button(
                                onClick = {
                                    userProfileViewModel.showHorseCreateWindow = true
                                },
                                shape = RoundedCornerShape(5.dp)
                            ) {
                                Text(stringResource(R.string.addHorse))
                            }

                            // Show the creation window if button is pressed
                            if (userProfileViewModel.showHorseCreateWindow) {
                                HorseCreateScreen(
                                    onConfirm = {
                                        userProfileViewModel.showHorseCreateWindow = false
                                        userProfileViewModel.getHorses(userId)
                                    },
                                    onDismiss = { userProfileViewModel.showHorseCreateWindow = false }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}
