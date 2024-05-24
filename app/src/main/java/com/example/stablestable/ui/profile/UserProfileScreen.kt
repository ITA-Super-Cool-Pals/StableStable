package com.example.stablestable.ui.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.stablestable.R
import com.example.stablestable.components.CreateScaffold
import com.example.stablestable.navigation.Screen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserProfileScreen(
    goToRiders: () -> Unit,
    goToHome: () -> Unit,
    goToShifts: () -> Unit,
    goToHorses: () -> Unit,
    goToMyProfile: () -> Unit,
    onLogout: () -> Unit,
    onHorseClick: (String) -> Unit,
    onArrowBack: () -> Unit,
    userId: String
){
    CreateScaffold(
        content = {paddingValues ->
            UserProfileScreenContent(userId, onHorseClick, paddingValues, onArrowBack)
        },
        goToHome = goToHome,
        goToRiders = goToRiders,
        goToShifts = goToShifts,
        goToHorses = goToHorses,
        goToMyProfile = goToMyProfile,
        onLogout = onLogout,
        screen = stringResource(R.string.riders),
        currentScreen = Screen.StableUsers.route

    )
}
