package com.example.stablestable.ui.shifts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.stablestable.R

@Composable
fun ShiftsSingleDayDialog(
    displayedDay: String,
    currentShiftName: String,
    dialog: String,
    onDismissRequest: () -> Unit,
    onAddMeClick: () -> Unit,
    onRemoveMeClick: () -> Unit
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

    Dialog(onDismissRequest = { onDismissRequest() },
        ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier
            .size(width = 300.dp, height = 250.dp)

        ) {
            Row {
                Text(text = weekDayList[0])
            }

            when(dialog){
                "empty" -> CardContentEmpty {onAddMeClick()}
                // TODO: NameOnShift skal ændres til det der egentlig er på shiftet
                "full" -> CardContentFull(currentShiftName){onRemoveMeClick()}
                else -> Text(text = "Error")
            }

            Box(Modifier.fillMaxSize()) {
                Text(modifier = Modifier
                    .clickable { onDismissRequest() }
                    .align(Alignment.BottomEnd), text = "LUK")
            }
        }
    }
}


@Composable
fun CardContentEmpty(
    onAddMeClick: () -> Unit
){
    Row(modifier= Modifier
        .fillMaxWidth()
        .padding(top = 6.dp),
        horizontalArrangement = Arrangement.Center) {
        Text(text = "This Shit empty", fontSize = 18.sp)

    }
    Row {
        Button(onClick = {
            onAddMeClick()
        }) {
            Text(text = "Add me to this shift")
        }
    }
}

@Composable
fun CardContentFull(
    nameOnShift: String,
    onRemoveMeClick: ()-> Unit
){
    Row(modifier= Modifier
        .fillMaxWidth()
        .padding(top = 6.dp),
        horizontalArrangement = Arrangement.Center) {
        Text(text = "This Shit Occupied: $nameOnShift", fontSize = 18.sp)

    }
    Row {
        Button(onClick = {
            onRemoveMeClick()
        }) {
            Text(text = "Remove me from this shift")
        }
    }
}



@Preview
@Composable
fun DialogPreview(){
    //ShiftsSingleDayDialog("Monday","full",{},{}){}
}
