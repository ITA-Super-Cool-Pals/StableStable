package com.example.stablestable.ui.shifts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShiftsScreenMatrix(
    onBoxOneClick:()->Unit
){
    val weekDayList: List<String> = listOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")
    Column(modifier = Modifier
        .padding(8.dp)
    ) {

        for (day in weekDayList){
            ShiftsSingleDay(currentShiftDay = day, onBoxOneClick = { onBoxOneClick() })
        }

    }
}

@Preview
@Composable
fun ShiftsScreenMatrixPreview(){
    ShiftsScreenMatrix({})
}

