package com.example.stablestable.ui.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun MyProfileScreen(
    navController: NavController
) {
    val profileViewModel = viewModel<ProfileViewModel>()
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
            Text(text = profileViewModel.fullname, fontSize = 26.sp, modifier = Modifier
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
            Text(text = profileViewModel.phone, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 26.dp),fontSize = 16.sp)

        }
        Row(modifier=Modifier.padding(start = 16.dp,top = 16.dp)) {
            Box(modifier = Modifier
                .height(height = 16.dp)
                .width(width = 16.dp)
                .background(Color.Blue)
                .align(Alignment.CenterVertically)) {
            }
            Text(text = "Mail:",fontSize = 16.sp)
            Text(text = profileViewModel.mail, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp),fontSize = 16.sp)

        }
        /*
        Button(onClick = { profileViewModel.fetchOne() },
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 60.dp)
                .padding(top = 16.dp)) {
            Text(text = "Click to Fetch data", color = Color.White)
        }
         */
    }
}

@Preview
@Composable
fun MyProfileScreenPreview(){
    MyProfileScreen(rememberNavController())
}
