package com.example.stablestable.ui.riders

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp


@Composable
fun SingleUserSmall(
    userName:String
){
    Row(modifier =Modifier
        .fillMaxWidth()
        .padding(16.dp)
    ){
        Box(modifier = Modifier
            .size(size = 50.dp)
            .background(Color.Red)){
        }
        Spacer(modifier = Modifier.width(width=16.dp))
        Text(text = userName, fontSize = 20.sp, modifier = Modifier.align(Alignment.CenterVertically))
    }
}

@Preview
@Composable
fun SingleUserSmallPreview(){
    SingleUserSmall("Name McNameson")
}
