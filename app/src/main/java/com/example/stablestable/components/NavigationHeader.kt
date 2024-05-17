package com.example.stablestable.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.stablestable.R

@Composable
fun NavigationHeader(
    goToHomeScreen:() -> Unit
){

    Row ( modifier = Modifier
        .fillMaxWidth()
        .padding(6.dp),
        horizontalArrangement = Arrangement.Start
    ){

        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "stuff",
            modifier = Modifier.size(30.dp).clickable {
                goToHomeScreen()
            }
        )

    }

}
