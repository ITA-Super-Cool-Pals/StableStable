package com.example.stablestable.ui.horses

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.stablestable.R
import com.example.stablestable.components.ArrowBack
import com.example.stablestable.components.CreateScaffold

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HorseProfileScreen(
    goToRiders: () -> Unit,
    goToHome: () -> Unit,
    goToShifts: () -> Unit,
    goToHorses: () -> Unit,
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
        onLogout = onLogout,
        screen = stringResource(R.string.horses)
    )
}

