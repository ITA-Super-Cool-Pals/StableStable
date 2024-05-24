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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stablestable.R
import com.example.stablestable.data.classes.Shift
import com.example.stablestable.ui.theme.StableStableTheme

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
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Arrow forward",
                modifier = Modifier
                    .size(35.dp)
                    .padding(end = 5.dp)
                    .clickable {
                        onPreviousWeek()
                    }
            )
            Text(
                text = "${stringResource(id = R.string.week)} $week", modifier = Modifier,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Arrow forward",
                modifier = Modifier
                    .size(35.dp)
                    .padding(start = 5.dp)
                    .clickable {
                        onNextWeek()
                    }
            )
        }
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp))

        Row {
            Spacer(modifier = Modifier.fillMaxWidth(0.39f))
            Text(text = stringResource(id = R.string.morning),
                fontStyle = FontStyle.Italic,
                fontSize = 14.sp)
            Spacer(modifier = Modifier.fillMaxWidth(0.49f))
            Text(text = stringResource(id = R.string.evening),
                fontStyle = FontStyle.Italic,
                fontSize = 14.sp)
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
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

@SuppressWarnings
@Preview
@Composable
fun VagtPrev() {
    StableStableTheme {
        ShiftsScreenContent(
            paddingValues = PaddingValues(6.dp),
            week = 13,
            shifts = listOf(Shift(
                13,
                3,
                "John",
                "morning"
            )),
            onPreviousWeek = { },
            onNextWeek = { }) { _, _, _, _ ->
        }
    }

}
