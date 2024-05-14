package com.example.stablestable.ui.horses

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.navigation.AuthViewModel

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

    Column {
        Text(text = "Horse name: ${viewModel.horseProfile.value.name}")
        Text(text = "Owner: ${viewModel.ownerProfile.value.firstName}")
    }
}
