package com.example.stablestable.ui.home

import androidx.compose.runtime.Composable
import com.example.stablestable.components.CreateScaffold

@Composable
fun HomeScreen(
    goToRiders: () -> Unit,
    goToHome: () -> Unit,
    goToShifts: () -> Unit,
    goToHorses: () -> Unit
) {
    CreateScaffold(
        content = {paddingValues ->
        CreateHomeScreen(paddingValues)
        },
        goToHome = goToHome,
        goToRiders = goToRiders,
        goToShifts = goToShifts,
        goToHorses = goToHorses
    )
}
