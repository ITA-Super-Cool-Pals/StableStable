package com.example.stablestable.ui.stable.horses

import androidx.compose.runtime.Composable
import com.example.stablestable.components.CreateScaffold

@Composable
fun StableHorsesScreen(
    goToRiders: () -> Unit,
    goToHome: () -> Unit,
    goToShifts: () -> Unit,
    goToHorses: () -> Unit,
    onHorseClick: (String) -> Unit
){
    CreateScaffold(
        content = {paddingValues ->
            StableHorsesScreenContent(paddingValues, onHorseClick)
        },
        goToHome = goToHome,
        goToRiders = goToRiders,
        goToShifts = goToShifts,
        goToHorses = goToHorses
    )
}
