package com.example.stablestable.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CreateBigButton(
    screen: String,
    onClick: () -> Unit
    ) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .padding(
                start = 14.dp,
                top = 7.dp,
                end = 14.dp,
                bottom = 7.dp),
        shape = RoundedCornerShape(5.dp)
    ) {
        Text(
            text = screen,
            fontSize = 35.sp)

    }
}
