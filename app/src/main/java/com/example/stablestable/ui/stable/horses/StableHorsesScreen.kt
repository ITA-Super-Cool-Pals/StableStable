package com.example.stablestable.ui.stable.horses

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.stablestable.R
import com.example.stablestable.components.CreateScaffold

@Composable
fun StableHorsesScreen(
    goToRiders: () -> Unit,
    goToHome: () -> Unit,
    goToShifts: () -> Unit,
    goToHorses: () -> Unit,
    goToBoard: () -> Unit,
    goToMyProfile: () -> Unit,
    currentScreen: String,
    onLogout: () -> Unit,
    onHorseClick: (String) -> Unit
){
    CreateScaffold(
        content = {paddingValues ->
            StableHorsesScreenContent(paddingValues, onHorseClick)
        },
        goToHome = goToHome,
        goToRiders = goToRiders,
        goToShifts = goToShifts,
        goToHorses = goToHorses,
        goToMyProfile = goToMyProfile,
        goToBoard = goToBoard,
        onLogout = onLogout,
        screen = stringResource(R.string.horses),
        currentScreen = currentScreen

    )
}
