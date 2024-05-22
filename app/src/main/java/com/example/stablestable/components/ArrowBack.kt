package com.example.stablestable.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stablestable.ui.theme.StableStableTheme

@Composable
fun ArrowBack(
    onArrowBack: () -> Unit
){

    Row(modifier = Modifier
        .fillMaxWidth()
    ) {
        Icon(modifier = Modifier
            .clickable { onArrowBack() },
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "Arrow Back")
    }

}

@Preview(
    //showBackground = true,
    showSystemUi = true
)
@Composable
fun ArrowBackPreview() {
    StableStableTheme {
        ArrowBack(){}
    }
}
