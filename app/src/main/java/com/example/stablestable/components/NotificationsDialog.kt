package com.example.stablestable.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.R
import com.example.stablestable.ui.home.HomeViewModel

@Composable
 fun NotificationsDialog(
    //listOfNotifications: List<String>,
    modifier: Modifier = Modifier
) {
    val viewModel = viewModel<HomeViewModel>()

    AlertDialog(
        onDismissRequest = {
            viewModel.setNotificationDialogStateToFalse()
        },
        title = {
            Text(
                text = stringResource(R.string.notifikationer)
            ) },
        text = {
            Text(
                text = "Test-notifikation 01"
            ) },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    viewModel.setNotificationDialogStateToFalse()
                }
            ) {
                Text(
                    text = stringResource(R.string.luk)
                )
            }
        },
        confirmButton = {

        }
    )
}
