package com.example.stablestable.ui.horses.horseDisplayBoxes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.R
import com.example.stablestable.ui.horses.HorseProfileViewModel

/*
 * Horse Feed info box
 * Code by Emily
 */

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HorseFeedBox(
    isOwner: Boolean,
    toggleDialog: () -> Unit
) {
    val viewModel: HorseProfileViewModel = viewModel()

    Box(
        modifier = Modifier
            .border(
                2.dp,
                MaterialTheme.colorScheme.secondaryContainer,
                RoundedCornerShape(10.dp, 10.dp, 10.dp, 10.dp)
            )
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp)
                    )
            ) {
                Row {
                    if (isOwner) {
                        // Extra spacing with same width as icon to properly center align text
                        Spacer(modifier = Modifier.width(24.dp))
                    }
                    Text(
                        text = stringResource(R.string.feedInfo),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .weight(1f)
                            .padding(vertical = 5.dp)
                    )
                    if (isOwner) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit feed information",
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(end = 10.dp)
                                .clickable { toggleDialog() }
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
            ) {
                // Roughage
                Text(text = stringResource(R.string.roughage), color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = viewModel.roughage,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Subsidy
                Text(text = stringResource(R.string.subsidy), color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = viewModel.subsidy,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Vitamins
                Text(text = stringResource(R.string.vitamins), color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = viewModel.vitamins,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Medicine
                Text(text = stringResource(R.string.medicine), color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = viewModel.medicine,
                    fontSize = 20.sp
                )
            }
        }
    }

    if (viewModel.showFeedEditDialog) {
        HorseFeedEditDialog(
            toggleDialog = toggleDialog
        )
    }
}
