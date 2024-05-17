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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stablestable.R

@Composable
fun HorseInfoBox(
    breed: String,
    sex: String,
    ageYears: Int,
    ageMonths: Int
) {
    Box(
        modifier = Modifier
            .border(1.dp, Color.Red, RoundedCornerShape(20.dp, 20.dp, 10.dp, 10.dp))
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
                Text(
                    text = stringResource(R.string.horseInfo),
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 10.dp)
            ) {
                // TODO: Change font colors to material colorscheme
                // Name
                Text(text = stringResource(R.string.breed), color = Color.Gray)
                Text(
                    text = breed,
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Phone
                Text(text = stringResource(R.string.sex), color = Color.Gray)
                Text(
                    text = sex,
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Age
                Text(text = stringResource(R.string.age), color = Color.Gray)
                Text(
                    text = "$ageYears ${stringResource(R.string.years)}, $ageMonths ${stringResource(R.string.months)}",
                    fontSize = 22.sp
                )
            }
        }
    }
}
