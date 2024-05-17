package com.example.stablestable.ui.shifts

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.data.classes.Shift

@Composable
fun ShiftsScreen(
    goToHomeScreen: () -> Unit, viewModel: ShiftsViewModel = viewModel<ShiftsViewModel>()
) {
    val shiftsWithFlowState = viewModel.getCurrentShifts(viewModel.currentWeek).collectAsState(emptyList())

    ShiftsScreenContent(
        week = viewModel.currentWeek,
        shifts = shiftsWithFlowState.value,
        goToHomeScreen = { goToHomeScreen() },
        onNextWeek = {
            viewModel.currentWeek ++
            viewModel.viewedWeek = viewModel.currentWeek },
        onPreviousWeek = {
            viewModel.currentWeek --
            viewModel.viewedWeek = viewModel.currentWeek },
        onBoxOneClick = { s: String, s1: String, s2: String, sh: Shift? ->
            viewModel.viewedDay = s
            viewModel.viewedSegment = s1
            viewModel.viewedUser = s2
            viewModel.openShiftDialog = true
            viewModel.dialogContentState = if (sh != null) {
                "full"
            } else {
                "empty"
            }
        })

    // Opening and closing the dialog
    when {
        viewModel.openShiftDialog -> {
            ShiftsSingleDayDialog(displayedDay = viewModel.viewedDay,
                currentShiftName = viewModel.viewedUser,
                dialog = viewModel.dialogContentState,
                onDismissRequest = { viewModel.openShiftDialog = false },
                onAddMeClick = {
                    viewModel.createShift()
                    viewModel.dialogContentState = "full"
                },
                onRemoveMeClick = {
                    viewModel.removeShift()
                    viewModel.dialogContentState = "empty"
                })
        }
    }

}

@Composable
fun ShiftsScreenContent(
    week: Int,
    shifts: List<Shift>,
    goToHomeScreen: () -> Unit,
    onPreviousWeek: () -> Unit,
    onNextWeek: () -> Unit,
    onBoxOneClick: (String, String, String, Shift?) -> Unit
) {

    Column {
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
                onBoxOneClick = { s: String, s1: String, s2: String, sh: Shift? ->
                    onBoxOneClick(
                        s, s1, s2, sh
                    )
                })

        }


    }


}

@Preview
@Composable
fun ShiftsScreenPreview() {
    ShiftsScreenContent(
        week = 21,
        shifts = listOf(
        Shift(
            13, "Monday", "John", "morning"
        ),
        Shift(
            13, "Friday", "Jane", "morning"
        ),
        Shift(
            13, "Tuesday", "John", "evening"
        ),
        Shift(
            13, "Sunday", "John", "evening"
        ),
    ), {},{},{}) { s, s1, s2, sh -> }
}

