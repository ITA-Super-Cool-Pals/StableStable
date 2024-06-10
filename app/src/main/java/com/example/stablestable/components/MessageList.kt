package com.example.stablestable.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.stablestable.data.classes.BoardMessage
import com.example.stablestable.data.classes.UserProfile

@Composable
fun MessageList(
    messages: List<BoardMessage>,
    userProfile: UserProfile?
) {
    LazyColumn {
        items(messages) { message ->
            MessageItem(
                timeStamp = message.timeStamp.toString(),
                message = message.content,
                userProfile = userProfile
            )
        }
    }
}
