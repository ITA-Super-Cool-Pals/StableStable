package com.example.stablestable.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stablestable.data.classes.UserProfile


@Composable
fun MessageItem(
    timeStamp: String,
    message: String,
    userProfile: UserProfile?
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(5.dp))
            .padding(8.dp)  // Inner padding inside the border
    ) {
        Column {
            Text(text = timeStamp, fontSize = 12.sp)
            userProfile?.let {
                Text(
                    text = "${it.firstName} ${it.lastName}",
                    fontSize = 18.sp
                )
            }
            Text(text = message, fontSize = 18.sp)
        }
    }
}
