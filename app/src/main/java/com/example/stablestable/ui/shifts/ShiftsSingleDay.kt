package com.example.stablestable.ui.shifts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stablestable.data.classes.Shift
import java.time.LocalDate

@Composable
fun ShiftsSingleDay(currentShiftDay: String ){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(48.dp)
    ){
        Text(modifier = Modifier
            .width(100.dp)
            .align(Alignment.CenterVertically)
            ,text = currentShiftDay)

        Divider(modifier = Modifier
            .fillMaxHeight()
            .width(1.dp)
            .padding(vertical = 4.dp)
        )

        Box(modifier = Modifier
            .fillMaxWidth(0.5f)
            .size(48.dp)

        ) {
            // TODO: Find en måde at indsætte en bruger her
            Text(text = "Box 1 content")
        }

        Divider(modifier = Modifier
            .fillMaxHeight()
            .width(1.dp)
            .padding(vertical = 4.dp)
        )

        Box(modifier = Modifier
            .fillMaxWidth(1.0f)
            .size(48.dp)
        ) {
            // TODO: Find en måde at indsætte en bruger her
            Text(text = "Box 2 Content")
        }



    }
}

@Preview
@Composable
fun ShiftsSingleDayPreview(){
    ShiftsSingleDay("sampleShift")
}


private val sampleShift = Shift(LocalDate.now(),"Mig","Morning")
