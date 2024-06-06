package com.example.stablestable.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable

@Composable
fun MessageList(messages: List<String>) {
    LazyColumn {
        items(messages) { message ->
            MessageItem(message)
        }
    }
}
