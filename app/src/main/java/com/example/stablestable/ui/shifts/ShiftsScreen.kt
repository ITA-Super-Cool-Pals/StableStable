package com.example.stablestable.ui.shifts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ShiftsScreen(

){
    val viewModel = viewModel<ShiftsViewModel>()

    Column() {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(height = 30.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Vagtplan")
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(height = 30.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            // TODO: Ændres, Man skal kunne vælge andre uger.
            Text(text = "Uge 13")
        }
        Divider(modifier = Modifier
            .padding(start = 100.dp, end = 100.dp),
            thickness = 2.dp
        )
        Row(modifier = Modifier
            .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // TODO: Indsæt Skema her.
            ShiftsScreenMatrix()
        }

    }

}

@Preview
@Composable
fun ShiftsScreenPreview(){
    ShiftsScreen()
}

