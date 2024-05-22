package com.example.stablestable.ui.shifts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stablestable.data.classes.Shift

@Composable
fun ShiftsScreenContent(
    paddingValues: PaddingValues,
    week: Int,
    shifts: List<Shift>,
    onPreviousWeek: () -> Unit,
    onNextWeek: () -> Unit,
    onShiftsBoxClick: (Int, String, String, Shift?) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "Vagtplan", fontSize = 36.sp, fontWeight = FontWeight.Medium)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(height = 30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // TODO: Ændres, Man skal kunne vælge andre uger.
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Arrow forward",
                modifier = Modifier
                    .size(25.dp)
                    .padding(end = 5.dp)
                    .clickable {
                        onPreviousWeek()
                    }
            )
            Text(text = "Uge ${week.toString()}", modifier = Modifier, fontWeight = FontWeight.Medium)
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Arrow forward",
                modifier = Modifier
                    .size(25.dp)
                    .padding(end = 5.dp)
                    .clickable {
                        onNextWeek()
                    }
            )
        }
        HorizontalDivider(
            modifier = Modifier.padding(start = 100.dp, end = 100.dp, bottom = 15.dp),
            thickness = 2.dp
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 100.dp)
                .padding(6.dp)
        ) {
            Spacer(modifier = Modifier.width(width = 80.dp))
            Text(
                modifier = Modifier.fillMaxWidth(0.5f),
                text = "Morning",
                textAlign = TextAlign.Center
            )

            Text(
                modifier = Modifier.fillMaxWidth(), text = "Aften", textAlign = TextAlign.Center
            )

        }
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            ShiftsScreenMatrix(
                shifts,
                onShiftsBoxClick = { i: Int, s: String, s1: String, sh: Shift? ->
                    onShiftsBoxClick(i, s, s1, sh)
                })

        }


    }

}

@Preview
@Composable
fun VagtPrev(){
    ShiftsScreenContent(
        paddingValues = PaddingValues(6.dp),
        week = 13,
        shifts = listOf(),
        onPreviousWeek = { /*TODO*/ },
        onNextWeek = { /*TODO*/ }) {
        s,s1,s2,s3 ->
    }
}
