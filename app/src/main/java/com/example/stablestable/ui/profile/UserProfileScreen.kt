package com.example.stablestable.ui.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.example.stablestable.components.CreateScaffold

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserProfileScreen(
    goToRiders: () -> Unit,
    goToHome: () -> Unit,
    goToShifts: () -> Unit,
    goToHorses: () -> Unit,
    onHorseClick: (String) -> Unit,
    userId: String
){
    CreateScaffold(
        content = {paddingValues ->
            UserProfileScreenContent(userId, onHorseClick, paddingValues)
        },
        goToHome = goToHome,
        goToRiders = goToRiders,
        goToShifts = goToShifts,
        goToHorses = goToHorses
    )
}
