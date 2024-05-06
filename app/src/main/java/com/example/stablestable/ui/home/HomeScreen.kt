package com.example.stablestable.ui.home

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.res.stringResource
import com.example.stablestable.R
import com.example.stablestable.components.CreateNotificationHeader
import com.example.stablestable.components.NotificationsDialog


@Composable
fun HomeScreen(
    goToProfile: () -> Unit
) {
    val viewModel = viewModel<HomeViewModel>()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 35.dp,
                top = 20.dp,
                end = 35.dp,
                bottom = 10.dp
            )

    ){
        CreateNotificationHeader(stableName = "Stald Navn")
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 15.dp,
                    top = 200.dp,
                    end = 15.dp,
                    bottom = 20.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Column{
                CreateHomeButton(
                    textOnButton = stringResource(R.string.profil),
                ) {
                    goToProfile()
                }
                CreateHomeButton(
                    textOnButton = stringResource(R.string.foderplan),
                ) {

                }
                CreateHomeButton(
                    textOnButton = stringResource(R.string.kalender),
                ) {

                }
                CreateHomeButton(
                    textOnButton = stringResource(R.string.faq),
                ) {

                }
            }
            Column{
                CreateHomeButton(
                    textOnButton = stringResource(R.string.vagtplan),
                ) {

                }
                CreateHomeButton(
                    textOnButton = stringResource(R.string.ryttere),
                ) {

                }
                CreateHomeButton(
                    textOnButton = stringResource(R.string.heste),
                ) {

                }
                CreateHomeButton(
                    textOnButton = stringResource(R.string.rytterstuen),
                ) {

                }
            }
        }
    }
    if (viewModel.showNotificationDialog) {
        NotificationsDialog()
    }
}

