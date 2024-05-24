package com.example.stablestable.ui.stable.riders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun StableUsersScreenContent(
    paddingValues: PaddingValues,
    onUserClick: (String) -> Unit) {

    val stableUsersViewModel: StableUsersViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        // Lazy Column for displaying the list of users
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = stableUsersViewModel.userList,
                itemContent = { user ->
                    val (userId, userName) = user
                    StableUserListItem(userName = userName, userId = userId, onUserClick = onUserClick)
                }
            )
        }
    }
}
