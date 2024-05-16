package com.example.stablestable.ui.horses

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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

/*
 * Code by Emily
 */

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HorseProfileScreen(horseId: String) {
    val viewModel: HorseProfileViewModel = viewModel()

    LaunchedEffect(horseId) {
        viewModel.getHorse(horseId)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the horse's name as title
        Text(
            text = viewModel.horseProfile.value.name,
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(10.dp))

        // General information about the horse
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth(0.85f)
        ) {
            // Owner information box
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
                            text = stringResource(R.string.ownerInfo),
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
                        // TODO: Change font colors to material colorscheme
                        // Name
                        Text(text = stringResource(R.string.name), color = Color.Gray)
                        Row {
                            Icon(
                                imageVector = Icons.Default.AccountCircle,
                                contentDescription = "Profile Picture",
                                modifier = Modifier
                                    .size(25.dp)
                                    .padding(end = 5.dp)
                                    .align(Alignment.CenterVertically)
                            )
                            Text(
                                text = "${viewModel.ownerProfile.value.firstName} ${viewModel.ownerProfile.value.lastName}",
                                fontSize = 22.sp,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

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
                            Text(
                                text = viewModel.ownerProfile.value.phone,
                                fontSize = 22.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

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
                            Text(
                                text = viewModel.ownerProfile.value.email,
                                fontSize = 22.sp
                            )
                        }

                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Horse information box
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
                            text = stringResource(R.string.horseInfo),
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
                        // TODO: Change font colors to material colorscheme
                        // Name
                        Text(text = stringResource(R.string.breed), color = Color.Gray)
                        Text(
                            text = viewModel.horseProfile.value.breed,
                            fontSize = 22.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Phone
                        Text(text = stringResource(R.string.sex), color = Color.Gray)
                        Text(
                            text = viewModel.horseProfile.value.sex,
                            fontSize = 22.sp
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Email
                        Text(text = stringResource(R.string.birthDate), color = Color.Gray)
                        Text(
                            text = "${viewModel.ageYears} ${stringResource(R.string.years)}, ${viewModel.ageMonths} ${stringResource(R.string.months)}",
                            fontSize = 22.sp
                        )
                    }
                }
            }
        }
    }
}
