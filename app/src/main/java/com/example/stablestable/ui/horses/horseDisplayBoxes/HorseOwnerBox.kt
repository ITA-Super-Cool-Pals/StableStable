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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Phone
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


/*
 * Horse Owner Box composable
 * Code by Emily
 */

@Composable
fun HorseOwnerBox(
    firstName: String,
    lastName: String,
    phone: String,
    email: String
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
                Text(
                    text = stringResource(R.string.ownerInfo),
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
                Text(text = stringResource(R.string.name), color = Color.Gray, fontSize = 14.sp)
                Row {
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(25.dp)
                            .padding(end = 5.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = "$firstName $lastName",
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Phone
                Text(text = stringResource(R.string.phone), color = Color.Gray, fontSize = 14.sp)
                Row {
                    Icon(
                        imageVector = Icons.Default.Phone,
                        contentDescription = "Phone",
                        modifier = Modifier
                            .size(25.dp)
                            .padding(end = 5.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = phone,
                        fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Email
                Text(text = stringResource(R.string.email), color = Color.Gray, fontSize = 14.sp)
                Row {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "Phone",
                        modifier = Modifier
                            .size(25.dp)
                            .padding(end = 5.dp)
                            .align(Alignment.CenterVertically)
                    )
                    Text(
                        text = email,
                        fontSize = 20.sp
                    )
                }

            }
        }
    }
}
