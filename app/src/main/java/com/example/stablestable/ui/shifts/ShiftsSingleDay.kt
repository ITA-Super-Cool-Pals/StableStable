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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stablestable.R
import com.example.stablestable.data.classes.Shift
import java.time.LocalDate

@Composable
fun ShiftsSingleDay(
    shifts: List<Shift>,
    currentShiftDay: Int,
    onShiftsBoxClick: (Int, String, String, Shift?) -> Unit
){
    val weekDayList: List<String> = listOf(
        stringResource(id = R.string.mon),
        stringResource(id = R.string.tue),
        stringResource(id = R.string.wed),
        stringResource(id = R.string.thu),
        stringResource(id = R.string.fri),
        stringResource(id = R.string.sat),
        stringResource(id = R.string.sun)
    )

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
            ,text = weekDayList[currentShiftDay])

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
                if (shiftMorn == null) {
                    onShiftsBoxClick(currentShiftDay, "morning", "", shiftMorn)
                } else {
                    onShiftsBoxClick(currentShiftDay, "morning", shiftMorn.user, shiftMorn)
                }
            }

        ) {
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
                if (shiftEve == null) {
                    onShiftsBoxClick(currentShiftDay, "evening", "", shiftEve)
                } else {
                    onShiftsBoxClick(currentShiftDay, "evening", shiftEve.user, shiftEve)
                }
            }
        ) {
            if (shiftEve != null) {
                Text(modifier = Modifier.align(Alignment.Center),text = shiftEve.user)
            }

        }



    }
}
/*
@Preview
@Composable
fun ShiftsSingleDayPreview(){
    ShiftsSingleDay(
        listOf(
            Shift(
                13,
                3,
                "John",
                "morning"
            )
        ),
        0
    ) { s, s1, s2,d -> }
}


*/
