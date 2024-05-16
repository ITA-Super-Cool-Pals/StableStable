package com.example.stablestable.ui.stable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp


@Composable
fun StableUserListItem(
    userName: String,
    userId: String,
    onUserClick: (String) -> Unit
){
    Row(
        modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 6.dp)
        .clickable { onUserClick(userId) }
    ) {
        // Profile image, default to standard icon
        Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Profile Picture",
            modifier = Modifier.size(50.dp)
        )
        // Spacer to separate the profile image and the user name
        Spacer(modifier = Modifier.width(width=16.dp))
        // The users full name
        Text(
            text = userName,
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
