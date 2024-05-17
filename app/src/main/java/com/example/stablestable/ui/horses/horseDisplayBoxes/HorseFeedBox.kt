package com.example.stablestable.ui.horses.horseDisplayBoxes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stablestable.R

@Composable
fun HorseFeedBox(
    isOwner: Boolean,
    roughage: String,
    subsidy: String,
    vitamins: String,
    medicine: String
) {
    Box(
        modifier = Modifier
            .border(1.dp, Color.LightGray, RoundedCornerShape(20.dp, 20.dp, 10.dp, 10.dp))
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.LightGray, // TODO: Change to material colorscheme
                        shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                    )
            ) {
                Row {
                    Text(
                        text = stringResource(R.string.feedInfo),
                        textAlign = TextAlign.Center,
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
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
            ) {
                // TODO: Change font colors to material colorscheme
                // Roughage
                Text(text = stringResource(R.string.roughage), color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = "roughage",
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Subsidy
                Text(text = stringResource(R.string.subsidy), color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = "subsidy",
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Vitamins
                Text(text = stringResource(R.string.vitamins), color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = "vitamins",
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Medicine
                Text(text = stringResource(R.string.medicine), color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = "medicine",
                    fontSize = 20.sp
                )
            }
        }
    }
}
