package com.example.stablestable.ui.home

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.Composable
import com.example.stablestable.components.CreateScaffold

@Composable
fun HomeScreen(
    goToProfile: () -> Unit,
    onLogout: () -> Unit
) {
    val viewModel = viewModel<HomeViewModel>()

    CreateScaffold()

}

/*
val viewModel = viewModel<HomeViewModel>()

            Column{
                CreateHomeButton(
                    textOnButton = stringResource(R.string.profil)
                ) {
                    goToProfile()

                }
            }

    if (viewModel.showNotificationDialog) {
        NotificationsDialog()
    }
 */
