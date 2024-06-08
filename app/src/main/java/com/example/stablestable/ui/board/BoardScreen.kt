package com.example.stablestable.ui.board

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.stablestable.R
import com.example.stablestable.components.CreateScaffold
//Lykke
@Composable
fun BoardScreen(
    goToRiders: () -> Unit,
    goToHome: () -> Unit,
    goToShifts: () -> Unit,
    goToHorses: () -> Unit,
    goToBoard: () -> Unit,
    currentScreen: String,
    goToMyProfile: () -> Unit,
    onLogout: () -> Unit
){
    CreateScaffold(
        content = {paddingValues ->
            BoardScreenContent(
                paddingValues,
            )
        },
        goToHome = goToHome,
        goToRiders = goToRiders,
        goToShifts = goToShifts,
        goToHorses = goToHorses,
        goToMyProfile = goToMyProfile,
        goToBoard = goToBoard,
        onLogout = onLogout,
        screen = stringResource(R.string.board),
        currentScreen = currentScreen
    )
}
