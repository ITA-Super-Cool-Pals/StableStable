package com.example.stablestable.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stablestable.ui.Screen

@Composable
fun MyProfileScreen(
    navController: NavController
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Row {
            // Profile pic + Name
            Box(modifier = Modifier
                .height(height = 50.dp)
                .width(width = 50.dp)
                .background(Color.Red)) {
            }
            Spacer(modifier = Modifier.width(width = 16.dp))
            Text(text = "Name McNameson", fontSize = 26.sp, modifier = Modifier
                .align(Alignment.CenterVertically))
        }
        Row(modifier=Modifier.padding(start = 16.dp,top = 50.dp)) {
            Box(modifier = Modifier
                .height(height = 16.dp)
                .width(width = 16.dp)
                .background(Color.Blue)
                .align(Alignment.CenterVertically)) {
            }
            Text(text = "Tlf:", fontSize = 16.sp, textAlign = TextAlign.Center)
            Text(text = "+45 11 11 11 11", modifier = Modifier.fillMaxWidth().padding(start = 26.dp),fontSize = 16.sp)

        }
        Row(modifier=Modifier.padding(start = 16.dp,top = 16.dp)) {
            Box(modifier = Modifier
                .height(height = 16.dp)
                .width(width = 16.dp)
                .background(Color.Blue)
                .align(Alignment.CenterVertically)) {
            }
            Text(text = "Mail:",fontSize = 16.sp)
            Text(text = "test@mail.dk", modifier = Modifier.fillMaxWidth().padding(start = 15.dp),fontSize = 16.sp)

        }
    }
}

@Preview
@Composable
fun MyProfileScreenPreview(){
    MyProfileScreen(rememberNavController())
}
