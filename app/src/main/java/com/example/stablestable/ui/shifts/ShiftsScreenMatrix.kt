package com.example.stablestable.ui.shifts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ShiftsScreenMatrix(){
    Column {

        Row(modifier = Modifier
            .fillMaxWidth()
        ) {
            Text(text = "Mondag")
            Box(modifier = Modifier){
                // TODO: Able to Insert user PP Morgen
            }
            Box(modifier = Modifier){
                // TODO: Able to Insert user  Aften
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
        ) {
            Text(text = "Tuesday")
            Box(modifier = Modifier){
                // TODO: Able to Insert user PP Morgen
            }
            Box(modifier = Modifier){
                // TODO: Able to Insert user  Aften
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
        ) {
            Text(text = "Wdnesday")
            Box(modifier = Modifier){
                // TODO: Able to Insert user PP Morgen
            }
            Box(modifier = Modifier){
                // TODO: Able to Insert user  Aften
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
        ) {
            Text(text = "Torsdag")
            Box(modifier = Modifier){
                // TODO: Able to Insert user PP Morgen
            }
            Box(modifier = Modifier){
                // TODO: Able to Insert user  Aften
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
        ) {
            Text(text = "Fredag")
            Box(modifier = Modifier){
                // TODO: Able to Insert user PP Morgen
            }
            Box(modifier = Modifier){
                // TODO: Able to Insert user  Aften
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
        ) {
            Text(text = "Lørdag")
            Box(modifier = Modifier){
                // TODO: Able to Insert user PP Morgen
            }
            Box(modifier = Modifier){
                // TODO: Able to Insert user  Aften
            }
        }
        Row(modifier = Modifier
            .fillMaxWidth()
        ) {
            Text(text = "Søndag")
            Box(modifier = Modifier){
                // TODO: Able to Insert user PP Morgen
            }
            Box(modifier = Modifier){
                // TODO: Able to Insert user  Aften
            }
        }
    }
}

@Preview
@Composable
fun ShiftsScreenMatrixPreview(){
    ShiftsScreenMatrix()
}
