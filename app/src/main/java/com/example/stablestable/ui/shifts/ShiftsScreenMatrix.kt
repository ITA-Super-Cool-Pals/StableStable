package com.example.stablestable.ui.shifts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stablestable.data.classes.Shift

/*
    Filen er skrevet af Josef

 */

@Composable
fun ShiftsScreenMatrix(
    shifts: List<Shift>, onShiftsBoxClick: (Int, String, String, Shift?) -> Unit
) {


    Column(
        modifier = Modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {

        for (day in 0..6) {

            val newShifts: List<Shift> = shifts.filter { it.dayOfWeek == day }

            ShiftsSingleDay(newShifts,
                day,
                onShiftsBoxClick = { i: Int, s: String, s1: String, sh: Shift? ->
                    onShiftsBoxClick(
                        i, s, s1, sh
                    )
                })
        }

    }
}


@Preview
@Composable
fun ShiftsScreenMatrixPreview() {
    ShiftsScreenMatrix(
        shifts = listOf(
            Shift(
                13, 0, "John", "morning"
            ),
            Shift(
                13, 4, "Jane", "morning"
            ),
            Shift(
                13, 1, "John", "evening"
            ),
            Shift(
                13, 6, "John", "evening"
            ),
        )
    ) { _, _, _, _ -> }
}

