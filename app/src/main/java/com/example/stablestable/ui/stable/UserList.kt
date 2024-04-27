package com.example.stablestable.ui.stable

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun StableUsers(
    navController: NavController,
) {
    val stableUsersViewModel = viewModel<StableUsersViewModel>()

    val users = stableUsersViewModel.nameList

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        item {
            Text(text = "List of all users in this stable", fontSize = 24.sp, fontWeight = FontWeight.Bold )
        }

        items(
            items = users,
            itemContent = {
                SingleUserSmall(userName = it)
            }
        )
    }
}




