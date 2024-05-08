package com.example.stablestable.ui.shifts

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ShiftsSingleDayDialog(
    onDismissRequest: () -> Unit
){
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier
            .size(width = 100.dp, height = 50.dp)

        ) {
            Text(modifier = Modifier.clickable{ onDismissRequest() },
                text = "LUK", )
        }
    }

}

@Preview
@Composable
fun DialogPreview(){
    ShiftsSingleDayDialog(){}
}
