package com.example.stablestable.ui.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.example.stablestable.components.CreateScaffold

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserProfileScreen(
    goToStable: () -> Unit,
    goToHome: () -> Unit,
    onHorseClick: (String) -> Unit,
    userId: String
){
    CreateScaffold(
        content = {paddingValues ->
            UserProfileScreenContent(userId, onHorseClick, paddingValues)
        },
        goToHome = goToHome,
        goToStable = goToStable
    )
}
