package com.example.stablestable.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp


@Composable
fun SingleUserSmall(
    userName: String
){
    Row(modifier =Modifier
        .fillMaxWidth()
        .padding(2.dp)
    ){
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Profile Picture",
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(width=16.dp))
        Text(text = userName, fontSize = 20.sp, modifier = Modifier.align(Alignment.CenterVertically))
    }
}
