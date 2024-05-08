package com.example.stablestable.ui.shifts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.components.NavigationHeader

@Composable
fun ShiftsScreen(
    goToHomeScreen:() -> Unit
){
    val viewModel = viewModel<ShiftsViewModel>()

    Column() {
        NavigationHeader(goToHomeScreen)
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Vagtplan", fontSize = 36.sp, fontWeight = FontWeight.Medium)
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
            .padding(start = 100.dp, end = 100.dp,bottom = 15.dp),
            thickness = 2.dp
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 100.dp)
            .padding(6.dp)
        ){
            Spacer(modifier = Modifier.width(width = 80.dp))
            Text(modifier = Modifier.fillMaxWidth(0.5f),
                text = "Morgen",textAlign = TextAlign.Center)
            Text(modifier= Modifier.fillMaxWidth(),
                text = "Aften", textAlign = TextAlign.Center)

        }
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
    ShiftsScreen({})
}

