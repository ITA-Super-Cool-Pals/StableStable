package com.example.stablestable.components

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
import androidx.compose.ui.window.Dialog
import com.example.stablestable.MainActivity


@Composable
fun CreatePermissionButtonWindow(onConfirm: () -> Unit){
    val mainActivity = MainActivity()
    Dialog(
        onDismissRequest = {}
    ){
        Button(
            onClick = {
                mainActivity.requestNotificationPermission()
                onConfirm()
            },
            modifier = Modifier
                .width(600.dp)
                .height(85.dp)
                .padding(10.dp), // Add padding between buttons
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Allow Stable Stable to send notifications",
                fontSize = 10.sp)
        }
    }
}
