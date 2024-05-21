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
    onLogout: () -> Unit,
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
        onLogout = onLogout,
        screen = stringResource(R.string.riders)

    )
}
