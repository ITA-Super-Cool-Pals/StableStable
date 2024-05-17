package com.example.stablestable.ui.horses

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.stablestable.ui.horses.horseDisplayBoxes.HorseFeedBox
import com.example.stablestable.ui.horses.horseDisplayBoxes.HorseInfoBox
import com.example.stablestable.ui.horses.horseDisplayBoxes.HorseOwnerBox

/*
 * Code by Emily
 */

@SuppressLint("StateFlowValueCalledInComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HorseProfileScreenContent(
    horseId: String,
    paddingValues: PaddingValues) {

    val viewModel: HorseProfileViewModel = viewModel()

    LaunchedEffect(horseId) {
        viewModel.getHorse(horseId)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Display the horse's name as title
            Text(
                text = viewModel.horseProfile.value.name,
                style = MaterialTheme.typography.headlineLarge
            )

            Spacer(modifier = Modifier.height(10.dp))

            // General information about the horse
            LazyColumn(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                item {
                    // Owner information box
                    HorseOwnerBox(
                        firstName = viewModel.ownerProfile.value.firstName,
                        lastName = viewModel.ownerProfile.value.lastName,
                        phone = viewModel.ownerProfile.value.phone,
                        email = viewModel.ownerProfile.value.email
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                }

                item {
                    // Horse information box
                    HorseInfoBox(
                        breed = viewModel.horseProfile.value.breed,
                        sex = viewModel.horseProfile.value.sex,
                        ageYears = viewModel.ageYears,
                        ageMonths = viewModel.ageMonths
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                }

                item {
                    // Horse feed box
                    HorseFeedBox(
                        isOwner = viewModel.isOwner,
                        roughage = viewModel.horseProfile.value.roughage,
                        subsidy = viewModel.horseProfile.value.subsidy,
                        vitamins = viewModel.horseProfile.value.vitamins,
                        medicine = viewModel.horseProfile.value.medicine
                    )
                }

            }
        }
    }
}
