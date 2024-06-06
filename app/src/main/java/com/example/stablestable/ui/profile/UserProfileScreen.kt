package com.example.stablestable.ui.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.stablestable.R
import com.example.stablestable.components.CreateScaffold

/*
    Filen er skrevet af Lykke

 */


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserProfileScreen(
    goToRiders: () -> Unit,
    goToHome: () -> Unit,
    goToShifts: () -> Unit,
    goToHorses: () -> Unit,
    goToMyProfile: () -> Unit,
    goToBoard: () -> Unit,
    onLogout: () -> Unit,
    onHorseClick: (String) -> Unit,
    onArrowBack: () -> Unit,
    currentScreen: String,
    userId: String
) {
    CreateScaffold(
        content = { paddingValues ->
            UserProfileScreenContent(userId, onHorseClick, paddingValues, onArrowBack)
        },
        goToHome = goToHome,
        goToRiders = goToRiders,
        goToShifts = goToShifts,
        goToHorses = goToHorses,
        goToMyProfile = goToMyProfile,
        goToBoard = goToBoard,
        onLogout = onLogout,
        screen = stringResource(R.string.riders),
        currentScreen = currentScreen

    )
}
