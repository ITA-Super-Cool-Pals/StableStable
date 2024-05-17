package com.example.stablestable.ui.stable.riders

import androidx.compose.runtime.Composable
import com.example.stablestable.components.CreateScaffold

@Composable
fun StableUsersScreen(
    goToStable: () -> Unit,
    goToHome: () -> Unit,
    onUserClick: (String) -> Unit
){
    CreateScaffold(
        content = {paddingValues ->
            StableUsersScreenContent(paddingValues, onUserClick)
        },
        goToHome = goToHome,
        goToStable = goToStable
    )
}
