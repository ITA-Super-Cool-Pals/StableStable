package com.example.stablestable.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.stablestable.R
import com.example.stablestable.components.CreateScaffold

//Lykke
@Composable
fun HomeScreen(
    goToRiders: () -> Unit,
    goToHome: () -> Unit,
    goToShifts: () -> Unit,
    goToHorses: () -> Unit,
    goToBoard: () -> Unit,
    currentScreen: String,
    goToMyProfile: () -> Unit,
    onLogout: () -> Unit
) {
    CreateScaffold(
        content = {paddingValues ->
        CreateHomeScreen(
            paddingValues,
            goToRiders = goToRiders,
            goToHorses = goToHorses,
            goToShifts = goToShifts,
            goToBoard = goToBoard
        )
        },
        goToHome = goToHome,
        goToRiders = goToRiders,
        goToShifts = goToShifts,
        goToHorses = goToHorses,
        goToBoard = goToBoard,
        goToMyProfile = goToMyProfile,
        onLogout = onLogout,
        screen = stringResource(R.string.home),
        currentScreen = currentScreen
    )
}
