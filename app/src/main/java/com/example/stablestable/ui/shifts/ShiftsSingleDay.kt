package com.example.stablestable.ui.shifts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stablestable.data.classes.Shift
import java.time.LocalDate

@Composable
fun ShiftsSingleDay(currentShift: Shift){
    Row(modifier = Modifier
        .fillMaxWidth()
        .sizeIn(maxHeight = 64.dp)
    ){
        Text(text = "${currentShift.date.dayOfWeek}")
    }
}

@Preview
@Composable
fun ShiftsSingleDayPreview(){
    ShiftsSingleDay(sampleShift)
}


private val sampleShift = Shift(LocalDate.now(),"Mig","Morning")
