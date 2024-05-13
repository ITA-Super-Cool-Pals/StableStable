package com.example.stablestable.ui.shifts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ShiftsScreenMatrix(
    onBoxOneClick:(String,String)->Unit
){
    val weekDayList: List<String> = listOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")

    Column(modifier = Modifier
        .padding(8.dp)
    ) {

        for (day in weekDayList){
            ShiftsSingleDay(currentShiftDay = day, onShiftsBoxClick = { s: String, s1: String -> onBoxOneClick(s, s1)})
        }

    }
}

@Preview
@Composable
fun ShiftsScreenMatrixPreview(){
    //ShiftsScreenMatrix(){}
}

