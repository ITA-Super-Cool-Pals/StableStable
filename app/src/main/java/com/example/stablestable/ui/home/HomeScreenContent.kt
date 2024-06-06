package com.example.stablestable.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.stablestable.R
import com.example.stablestable.components.CreateBigButton
//Lykke
@Composable
fun CreateHomeScreen(
    paddingValues: PaddingValues,
    goToRiders: () -> Unit,
    goToHorses: () -> Unit,
    goToShifts: () -> Unit,
    goToBoard: () -> Unit
    ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentAlignment = Alignment.Center
    ) {
        Column {
            CreateBigButton(
                screen = stringResource(R.string.riders),
                goToRiders
            )
            CreateBigButton(
                screen = stringResource(R.string.horses),
                goToHorses
            )
            CreateBigButton(
                screen = stringResource(R.string.shifts),
                goToShifts
            )
            CreateBigButton(screen = stringResource(R.string.board),
                goToBoard)
        }
    }
}

