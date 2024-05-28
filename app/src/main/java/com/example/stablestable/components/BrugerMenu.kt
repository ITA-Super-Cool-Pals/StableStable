package com.example.stablestable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stablestable.R

@Composable
fun BurgerMenu(
    onNavigate: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(24.dp))
                Text(
                    text = stringResource(R.string.appName),
                    fontSize = 26.sp,
                    style = MaterialTheme.typography.headlineMedium
                )
                Icon(
                    imageVector = Icons.Filled.Close, contentDescription = "Close menu"
                )
            }
            Spacer(
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 20.dp)
                    .height(1.dp)
                    .fillMaxWidth(0.9f)
                    .background(MaterialTheme.colorScheme.outline)
                    .align(Alignment.CenterHorizontally)
            )
            Row(modifier = Modifier
                .clickable { onNavigate("Profile") }
                .fillMaxWidth()) {
                Icon(
                    imageVector = Icons.Filled.Person, contentDescription = "My Profile"
                )
                Text(
                    text = stringResource(R.string.myProfile),
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier
                .clickable { onNavigate("Logout") }
                .fillMaxWidth()) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Logout, contentDescription = "Logout"
                )
                Text(
                    text = stringResource(R.string.logout),
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}
