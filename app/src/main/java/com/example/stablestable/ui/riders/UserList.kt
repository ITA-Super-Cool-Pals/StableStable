package com.example.stablestable.ui.riders

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.navigation.AuthViewModel

@Composable
fun StableUsers(

) {
    val authViewModel: AuthViewModel = viewModel()
    val stableUsersViewModel: StableUsersViewModel = viewModel(factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return StableUsersViewModel(authViewModel) as T
        }
    })

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




