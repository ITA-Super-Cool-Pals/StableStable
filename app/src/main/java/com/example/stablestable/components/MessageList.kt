package com.example.stablestable.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.stablestable.data.classes.BoardMessage

@Composable
fun MessageList(
    messages: List<BoardMessage>
) {
    LazyColumn {
        items(messages) { message ->
            MessageItem(
                timeStamp = message.timeStamp.toString(),
                message = message.content,
                sender = message.userName
            )
        }
    }
}
