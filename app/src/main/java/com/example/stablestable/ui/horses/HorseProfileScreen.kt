package com.example.stablestable.ui.horses

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.example.stablestable.components.CreateScaffold

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HorseProfileScreen(
    goToRiders: () -> Unit,
    goToHome: () -> Unit,
    horseId: String
){
    CreateScaffold(
        content = {paddingValues ->
            HorseProfileScreenContent(horseId, paddingValues)
        },
        goToHome = goToHome,
        goToRiders = goToRiders
    )
}

