package com.example.stablestable.ui.horses.horseDisplayBoxes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.R
import com.example.stablestable.ui.horses.HorseProfileViewModel

/*
 * Horse feed edit dialog
 * Code by Emily
 */

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HorseFeedEditDialog(
    toggleDialog: () -> Unit
) {
    val viewModel: HorseProfileViewModel = viewModel()

    Dialog(
        onDismissRequest = { toggleDialog() }
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(10.dp))
                .padding(25.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    stringResource(R.string.editFeed),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center
                )

                OutlinedTextField(
                    value = viewModel.roughage,
                    onValueChange = { viewModel.roughage = it },
                    label = { Text(stringResource(R.string.roughage)) }
                )
                OutlinedTextField(
                    value = viewModel.subsidy,
                    onValueChange = { viewModel.subsidy = it },
                    label = { Text(stringResource(R.string.subsidy)) }
                )
                OutlinedTextField(
                    value = viewModel.vitamins,
                    onValueChange = { viewModel.vitamins = it },
                    label = { Text(stringResource(R.string.vitamins)) }
                )
                OutlinedTextField(
                    value = viewModel.medicine,
                    onValueChange = { viewModel.medicine = it },
                    label = { Text(stringResource(R.string.medicine)) }
                )

                // Save button
                Button(
                    onClick = {
                        viewModel.updateFeed()
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier
                        .padding(top = 20.dp)
                ) {
                    Text(stringResource(R.string.confirm))
                }
            }
        }
    }
}
