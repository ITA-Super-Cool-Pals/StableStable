package com.example.stablestable.ui.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.stablestable.R
import com.example.stablestable.components.CreateScaffold

@Composable
fun HomeScreen(
    goToRiders: () -> Unit,
    goToHome: () -> Unit,
    goToShifts: () -> Unit,
    goToHorses: () -> Unit,
    goToMyProfile: () -> Unit,
    onLogout: () -> Unit
) {
    CreateScaffold(
        content = {paddingValues ->
        CreateHomeScreen(
            paddingValues,
            goToRiders = goToRiders,
            goToHorses = goToHorses,
            goToShifts = goToShifts
        )
        },
        goToHome = goToHome,
        goToRiders = goToRiders,
        goToShifts = goToShifts,
        goToHorses = goToHorses,
        goToMyProfile = goToMyProfile,
        onLogout = onLogout,
        screen = stringResource(R.string.home)
    )
}
