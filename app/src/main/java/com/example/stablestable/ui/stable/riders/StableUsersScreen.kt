package com.example.stablestable.ui.stable.riders

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.stablestable.R
import com.example.stablestable.components.CreateScaffold

@Composable
fun StableUsersScreen(
    goToRiders: () -> Unit,
    goToHome: () -> Unit,
    goToShifts: () -> Unit,
    goToHorses: () -> Unit,
    goToMyProfile: () -> Unit,
    goToBoard: () -> Unit,
    onLogout: () -> Unit,
    currentScreen: String,
    onUserClick: (String) -> Unit
){
    CreateScaffold(
        content = {paddingValues ->
            StableUsersScreenContent(paddingValues, onUserClick)
        },
        goToHome = goToHome,
        goToRiders = goToRiders,
        goToShifts = goToShifts,
        goToHorses = goToHorses,
        goToMyProfile = goToMyProfile,
        onLogout = onLogout,
        goToBoard = goToBoard,
        screen = stringResource(R.string.riders),
        currentScreen = currentScreen
    )
}
