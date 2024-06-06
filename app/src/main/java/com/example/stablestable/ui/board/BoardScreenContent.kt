package com.example.stablestable.ui.board

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.components.MessageList

@Composable
fun BoardScreenContent(
    paddingValues: PaddingValues
){
    val boardViewModel: BoardViewModel = viewModel()
    val sampleMessages = List(100) { "Message #$it" }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        MessageList(messages = sampleMessages)
    }

}
