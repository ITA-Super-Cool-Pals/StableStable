package com.example.stablestable.ui.horses.horseDisplayBoxes

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stablestable.R

/*
 * Horse general information box
 * Code by Emily
 */

@Composable
fun HorseInfoBox(
    breed: String,
    sex: String,
    ageYears: Int,
    ageMonths: Int
) {
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
                Text(
                    text = stringResource(R.string.horseInfo),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
            ) {
                // Name
                Text(text = stringResource(R.string.breed), color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = breed,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Phone
                Text(text = stringResource(R.string.sex), color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = sex,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Age
                Text(text = stringResource(R.string.age), color = Color.Gray, fontSize = 14.sp)
                Text(
                    text = "$ageYears ${stringResource(R.string.years)}, $ageMonths ${stringResource(R.string.months)}",
                    fontSize = 20.sp
                )
            }
        }
    }
}
