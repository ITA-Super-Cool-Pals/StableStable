package com.example.stablestable.ui.profile

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.stablestable.R
import com.example.stablestable.navigation.AuthViewModel
import com.example.stablestable.ui.horses.HorseCreateScreen


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyProfileScreen(onHorseClick: (String) -> Unit) {
    val profileViewModel: ProfileViewModel = viewModel()

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Row {
            // Profile pic + Name
            Image(
                painter = painterResource(R.drawable.profile_placeholder),
                contentDescription = "Placeholder Profile pic",
                modifier = Modifier
                    .size(100.dp) // Adjust the size as needed
            )
            Spacer(modifier = Modifier.width(width = 16.dp))
            Text(text = profileViewModel.fullName, fontSize = 26.sp, modifier = Modifier
                .align(Alignment.CenterVertically))
        }
        Row(modifier=Modifier.padding(start = 16.dp,top = 50.dp)) {
            Text(text = "Tlf:", fontSize = 16.sp, textAlign = TextAlign.Center)
            Text(text = profileViewModel.phone, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 26.dp),fontSize = 16.sp)

        }
        Row(modifier=Modifier.padding(start = 16.dp,top = 16.dp)) {
            Text(text = "Mail:",fontSize = 16.sp)
            Text(text = profileViewModel.email, modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp),fontSize = 16.sp)

        }

        Spacer(modifier = Modifier.height(20.dp))

        // My Horses section
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 50.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.myHorses),
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                )

                Button(
                    onClick = {
                        profileViewModel.showHorseCreateWindow = true
                    },
                    shape = RoundedCornerShape(5.dp)
                ) {
                    Text(stringResource(R.string.addHorse))
                }
            }

            Column {
                profileViewModel.horseList.forEach { horseItem ->
                    Text(
                        text = horseItem.horseName,
                        modifier = Modifier.clickable {
                            onHorseClick(horseItem.horseId)
                        }
                    )
                }
            }

            if (profileViewModel.showHorseCreateWindow) {
                HorseCreateScreen(
                    onConfirm = {
                        profileViewModel.showHorseCreateWindow = false
                        profileViewModel.getHorses()
                    },
                    onDismiss = { profileViewModel.showHorseCreateWindow = false }
                )
            }
        }
    }
}
