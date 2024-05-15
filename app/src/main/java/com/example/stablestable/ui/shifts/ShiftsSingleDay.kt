package com.example.stablestable.ui.shifts

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stablestable.data.classes.Shift
import java.time.LocalDate

@Composable
fun ShiftsSingleDay(
    shifts: List<Shift>,
    currentShiftDay: String,
    onShiftsBoxClick: (String, String, Shift?) -> Unit
){
    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(max = 80.dp)
        .padding(2.dp)
        .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
        .background(Color(0x61CDDC39), RoundedCornerShape(8.dp))
    ){
        val shiftEve:Shift? = shifts.find { it.shiftTime == "evening" }
        val shiftMorn:Shift? = shifts.find { it.shiftTime == "morning" }


        Text(modifier = Modifier
            .width(100.dp)
            .align(Alignment.CenterVertically)
            .padding(8.dp)
            ,text = currentShiftDay)

        HorizontalDivider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .padding(vertical = 4.dp)
        )

        Box(modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight()
            //.padding(8.dp)
            .size(48.dp)
            .clickable {
                onShiftsBoxClick(currentShiftDay, "morning",shiftMorn)
            }

        ) {
            // TODO: Find en måde at indsætte en bruger her
            if (shiftMorn != null) {
                Text(modifier = Modifier.align(Alignment.Center),text = shiftMorn.user)
            }

        }

        HorizontalDivider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp)
                .padding(vertical = 4.dp)
        )

        Box(modifier = Modifier
            .fillMaxWidth(1.0f)
            .fillMaxHeight()
            //.padding(8.dp)
            .size(48.dp)
            .clickable {
                onShiftsBoxClick(currentShiftDay, "evening",shiftEve)
            }
        ) {
            // TODO: Find en måde at indsætte en bruger her
            if (shiftEve != null) {
                Text(modifier = Modifier.align(Alignment.Center),text = shiftEve.user)
            }

        }



    }
}

@Preview
@Composable
fun ShiftsSingleDayPreview(){
    ShiftsSingleDay(
        listOf(
            Shift(
                13,
                "Monday",
                "John",
                "morning"
            )
        ),
        "Monday"
    ) { s, s1,d -> }
}


