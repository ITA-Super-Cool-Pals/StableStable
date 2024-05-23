package com.example.stablestable.ui.shifts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.stablestable.R
import com.example.stablestable.ui.theme.StableStableTheme
import java.util.Locale

@Composable
fun ShiftsSingleDayDialog(
    displayedDay: Int,
    displayedTime: String,
    currentShiftName: String,
    dialog: String,
    isMyShift: Boolean,
    onDismissRequest: () -> Unit,
    onAddMeClick: () -> Unit,
    onRemoveMeClick: () -> Unit
) {
    val weekDayList: List<String> = listOf(
        stringResource(id = R.string.mon),
        stringResource(id = R.string.tue),
        stringResource(id = R.string.wed),
        stringResource(id = R.string.thu),
        stringResource(id = R.string.fri),
        stringResource(id = R.string.sat),
        stringResource(id = R.string.sun)
    )

    Dialog(
        onDismissRequest = { onDismissRequest() },
    ) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            modifier = Modifier
                .size(width = 300.dp, height = 250.dp)


        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.tertiaryContainer),
                horizontalArrangement = Arrangement.SpaceAround

            ) {
                Text(text = weekDayList[displayedDay], fontWeight = FontWeight.Medium)
                Text(
                    text = displayedTime.replaceFirstChar { it.uppercase() },
                    fontWeight = FontWeight.Medium
                )
            }

            when (dialog) {
                "empty" -> CardContentEmpty { onAddMeClick() }
                "full" -> CardContentFull(currentShiftName, isMyShift) { onRemoveMeClick() }
                else -> Text(text = stringResource(id = R.string.error))
            }

            Box(Modifier.fillMaxSize()) {
                Button(
                    onClick = { onDismissRequest() },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                ) {
                    Text(text = stringResource(id = R.string.close))
                }
            }
        }
    }
}


@Composable
fun CardContentEmpty(
    onAddMeClick: () -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(id = R.string.empty_shift), fontSize = 18.sp)

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { onAddMeClick() }) {
                Text(text = stringResource(id = R.string.add_shift))
            }
        }

    }
}

@Composable
fun CardContentFull(
    nameOnShift: String,
    isMyShift: Boolean,
    onRemoveMeClick: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(6.dp)) {
        Text(
            text = "${stringResource(id = R.string.full_shift)}:",
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = nameOnShift,
            fontSize = 18.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        if (isMyShift) {
            Button(modifier = Modifier
                .align(Alignment.CenterHorizontally),
                onClick = { onRemoveMeClick() }
            ) {
                Text(text = stringResource(id = R.string.remove_shift))
            }
        }

    }
}


@Preview
@Composable
fun DialogPreview() {
    StableStableTheme {
        ShiftsSingleDayDialog(
            displayedDay = 6,
            displayedTime = "Evening",
            currentShiftName = "John",
            dialog = "full",
            isMyShift = false,
            {}, {}) {}
    }
}

@Preview
@Composable
fun DialogPreview2() {
    StableStableTheme {
        CardContentFull("John", false) {

        }
    }
}
