package com.example.stablestable.ui.home

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun CreateHomeButton(
    textOnButton: String,
    onClickFun: () -> Unit
){
    Button(
        onClick = {
            onClickFun()
        },
        modifier = Modifier
            .width(130.dp)
            .height(150.dp)
            .padding(vertical = 20.dp), // Add padding between buttons
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = textOnButton,
            fontSize = 15.sp)
    }
}
