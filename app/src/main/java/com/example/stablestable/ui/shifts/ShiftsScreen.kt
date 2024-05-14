package com.example.stablestable.ui.shifts

import android.content.ContentValues.TAG
import android.util.Log
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.stablestable.components.NavigationHeader
import com.example.stablestable.data.classes.Shift

@Composable
fun ShiftsScreen(
    goToHomeScreen:() -> Unit
){
    val viewModel = viewModel<ShiftsViewModel>()

    val shiftsWithFlowState = viewModel.shiftsWithFlow.collectAsStateWithLifecycle(emptyList())
    val shiftsWithFlowValues = shiftsWithFlowState.value
    val shift1 = shiftsWithFlowValues[0]
    // TODO: Need to figure out how to use these values
    //Log.d(TAG,"${shiftsWithFlowValues[1].weekNumber}")

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        NavigationHeader(goToHomeScreen)
        Row(modifier = Modifier
            .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = shiftsWithFlowValues.toString(), fontSize = 36.sp, fontWeight = FontWeight.Medium)
        }
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(height = 30.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            // TODO: Ændres, Man skal kunne vælge andre uger.
            Text(text = "Uge 13")
        }
        Divider(modifier = Modifier
            .padding(start = 100.dp, end = 100.dp,bottom = 15.dp),
            thickness = 2.dp
        )
        Row(modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 100.dp)
            .padding(6.dp)
        ){
            Spacer(modifier = Modifier.width(width = 80.dp))
            Text(modifier = Modifier.fillMaxWidth(0.5f),
                text = "Morning",textAlign = TextAlign.Center)

            Text(modifier= Modifier.fillMaxWidth(),
                text = "Aften", textAlign = TextAlign.Center)

        }
        Row(modifier = Modifier
            .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            // TODO: Indsæt Skema her.
            ShiftsScreenMatrix(onBoxOneClick = {s: String, s1:String ->
                viewModel.viewedDay = s
                viewModel.viewedSegment = s1
                viewModel.openShiftDialog = true }
            )
        }

    }


    when {
        viewModel.openShiftDialog -> {
            val shiftCode = viewModel.currentShift.shiftCode
            ShiftsSingleDayDialog(
                displayedDay = viewModel.viewedDay,
                //TODO: dialog needs to change to "full", if the day is occupied
                dialog = "empty",
                onDismissRequest = {viewModel.openShiftDialog = false},
                onAddMeClick = { viewModel.createShift() },
                onRemoveMeClick = { viewModel.removeShift() })
        }
    }


}

@Preview
@Composable
fun ShiftsScreenPreview(){
    ShiftsScreen(){}
}

