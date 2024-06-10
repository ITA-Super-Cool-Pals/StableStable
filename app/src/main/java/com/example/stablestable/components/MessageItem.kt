package com.example.stablestable.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MessageItem(
    timeStamp: String,
    message: String,
    sender: String
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
            Text(
                text = sender,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.primary
            )
            Text(text = message, fontSize = 18.sp)
        }
    }
}
