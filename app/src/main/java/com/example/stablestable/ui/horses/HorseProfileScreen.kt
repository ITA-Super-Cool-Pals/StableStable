package com.example.stablestable.ui.horses

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HorseProfileScreen(horseId: String) {
    val horseViewModel: HorseViewModel = viewModel()

    LaunchedEffect(horseId) {
        horseViewModel.getHorse(horseId)
    }

    Text(text = "Horse name: ${horseViewModel.horseProfile.value.name}")
}
