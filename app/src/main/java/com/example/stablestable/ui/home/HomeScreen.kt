package com.example.stablestable.ui.home

import androidx.compose.runtime.Composable
import com.example.stablestable.components.CreateScaffold

@Composable
fun HomeScreen(
    goToStable: () -> Unit,
    goToHome: () -> Unit
    //goToShifts: () -> Unit
) {
    CreateScaffold(
        content = {paddingValues ->
        CreateHomeScreen(paddingValues)
        },
        {goToStable()},
        {goToHome()}
    )
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
 */
