package com.example.stablestable.ui.board

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.R
import com.example.stablestable.components.MessageList
import com.example.stablestable.data.classes.UserProfile

@Composable
fun BoardScreenContent(
    paddingValues: PaddingValues
){
    val viewModel: BoardViewModel = viewModel()

    // Load messages when the composable is first displayed
    LaunchedEffect(Unit) {
        val userId = viewModel.userId
        viewModel.getCurrentBoardMessages()
        if (userId.isNotEmpty()) {
            viewModel.fetchUserProfile(userId)
        }
    }

    val messages = viewModel.boardMessages.collectAsState().value
    val userProfile: UserProfile? = viewModel.userProfile.collectAsState().value


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight() // Wrap content height to avoid filling the entire screen height
                .padding(horizontal = 16.dp), // Add horizontal padding to center the input field
            horizontalAlignment = Alignment.CenterHorizontally
        ){

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                value = viewModel.inputFieldText.value,
                onValueChange = { viewModel.inputFieldText.value = it },
                label = { Text(stringResource(R.string.writePost)) },
                textStyle = androidx.compose.ui.text.TextStyle(fontSize = 18.sp),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Send,
                        contentDescription = stringResource(R.string.writePost),
                        modifier = Modifier.clickable {
                            viewModel.createBoardMessage()
                            viewModel.inputFieldText.value = ""
                        }
                    )
                }
            )

            MessageList(messages = messages, userProfile = userProfile)
        }
    }

}
