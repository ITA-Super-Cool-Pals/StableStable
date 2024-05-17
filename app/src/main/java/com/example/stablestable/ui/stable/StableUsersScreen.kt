package com.example.stablestable.ui.stable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun StableUsersScreen(onUserClick: (String) -> Unit) {
    val stableUsersViewModel: StableUsersViewModel = viewModel()

    Column {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, bottom = 20.dp)
        ) {
            Text(
                text = "List of all users in this stable",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(1.dp)
                    .background(Color.Gray)
            )
        }

        // Column for displaying the list of users
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