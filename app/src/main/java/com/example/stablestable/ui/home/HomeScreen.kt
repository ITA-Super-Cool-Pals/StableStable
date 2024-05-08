package com.example.stablestable.ui.home

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.TextButton
import androidx.compose.ui.res.stringResource
import com.example.stablestable.R
import com.example.stablestable.components.CreateNotificationHeader
import com.example.stablestable.components.NotificationsDialog

@Composable
fun HomeScreen(
    goToProfile: () -> Unit,
    goToRiders: () -> Unit,
    goToShifts: () -> Unit,
    onLogout: () -> Unit
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
        CreateNotificationHeader(stableName = "Stald Navn") { onLogout() }
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
                    textOnButton = stringResource(R.string.feedingSchedule),
                ) {

                }
                CreateHomeButton(
                    textOnButton = stringResource(R.string.calendar),
                ) {

                }
                CreateHomeButton(
                    textOnButton = stringResource(R.string.faq),
                ) {

                }
            }
            Column{
                CreateHomeButton(
                    textOnButton = stringResource(R.string.shifts),
                ) {
                    goToShifts()
                }
                CreateHomeButton(
                    textOnButton = stringResource(R.string.riders),
                ) {
                    goToRiders()
                }
                CreateHomeButton(
                    textOnButton = stringResource(R.string.horses),
                ) {

                }
                CreateHomeButton(
                    textOnButton = stringResource(R.string.ridersRoom),
                ) {

                }
            }
        }
    }
    if (viewModel.showNotificationDialog) {
        NotificationsDialog()
    }
}

