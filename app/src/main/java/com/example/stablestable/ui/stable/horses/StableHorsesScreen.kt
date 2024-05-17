package com.example.stablestable.ui.stable.horses

import androidx.compose.runtime.Composable
import com.example.stablestable.components.CreateScaffold
import com.example.stablestable.ui.stable.riders.StableUsersScreenContent

@Composable
fun StableHorsesScreen(
    goToRiders: () -> Unit,
    goToHome: () -> Unit,
    onHorseClick: (String) -> Unit
){
    CreateScaffold(
        content = {paddingValues ->
            StableUsersScreenContent(paddingValues, onHorseClick)
        },
        goToHome = goToHome,
        goToRiders = goToRiders
    )
}
