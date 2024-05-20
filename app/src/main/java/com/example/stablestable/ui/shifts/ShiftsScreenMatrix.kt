package com.example.stablestable.ui.shifts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stablestable.data.classes.Shift

@Composable
fun ShiftsScreenMatrix(
    shifts: List<Shift>,
    onBoxOneClick:(String,String,String,Shift?)->Unit
){
    val weekDayList: List<String> = listOf("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday")

    Column(modifier = Modifier
        .padding(8.dp)
    ) {

        for (day in weekDayList){

            val newShifts:List<Shift> = shifts.filter { it.dayOfWeek == day }

            ShiftsSingleDay(newShifts, day, onShiftsBoxClick = { s: String, s1: String,s2: String, sh:Shift? -> onBoxOneClick(s, s1,s2,sh)})
        }

    }
}

@Preview
@Composable
fun ShiftsScreenMatrixPreview(){
    ShiftsScreenMatrix(
        shifts = listOf(
            Shift(
                13,
                "Monday",
                "John",
                "morning"
            ),
            Shift(
                13,
                "Friday",
                "Jane",
                "morning"
            ),
            Shift(
                13,
                "Tuesday",
                "John",
                "evening"
            ),
            Shift(
                13,
                "Sunday",
                "John",
                "evening"
            ),
        )
    ){s, s1, s2, sh ->}
}

