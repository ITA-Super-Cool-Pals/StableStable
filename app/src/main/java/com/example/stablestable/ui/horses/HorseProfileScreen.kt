package com.example.stablestable.ui.horses

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.stablestable.R
import com.example.stablestable.components.CreateScaffold

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HorseProfileScreen(
    goToRiders: () -> Unit,
    goToHome: () -> Unit,
    goToShifts: () -> Unit,
    goToHorses: () -> Unit,
    goToMyProfile: () -> Unit,
    goToBoard: () -> Unit,
    currentScreen: String,
    onLogout: () -> Unit,
    onArrowBack: () -> Unit,
    horseId: String
){
    CreateScaffold(
        content = {paddingValues ->
            HorseProfileScreenContent(horseId, paddingValues){ onArrowBack()}
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

