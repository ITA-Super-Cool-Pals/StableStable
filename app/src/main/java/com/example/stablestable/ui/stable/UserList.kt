package com.example.stablestable.ui.stable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun StableUsers(
    navController: NavController,
) {
    val stableUsersViewModel = viewModel<StableUsersViewModel>()

    val users = remember { stableUsersViewModel.nameList }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            items = users,
            itemContent = {
                SingleUserSmall(userName = it)
            }
        )
    }
}




