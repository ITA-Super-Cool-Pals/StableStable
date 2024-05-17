package com.example.stablestable.ui.stable.horses

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CrueltyFree
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stablestable.data.classes.HorseItem

@Composable
fun StableHorseListItem(
    horse: HorseItem,
    onHorseClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onHorseClick(horse.horseId) }
    ) {
        // Horse image, default to standard icon
        Icon(
            imageVector = Icons.Default.CrueltyFree,
            contentDescription = "Profile Picture",
            modifier = Modifier.size(50.dp)
        )
        // Spacer to separate the horse image and the horse name
        Spacer(modifier = Modifier.width(width=16.dp))
        Text(
            text = horse.horseName,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(1f)

        )
        // Visibility icon
        Icon(
            imageVector = Icons.Default.Visibility,
            contentDescription = "View Profile",
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
    }
}
