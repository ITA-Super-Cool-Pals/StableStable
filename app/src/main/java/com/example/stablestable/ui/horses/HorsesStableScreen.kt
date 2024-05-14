package com.example.stablestable.ui.horses

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HorsesStableScreen(onHorseClick: (String) -> Unit) {
    val viewModel: HorsesStableViewModel = viewModel()

    Column {
        Text(text = "List of all horses in stable")
        
        viewModel.horseList.forEach { horseItem ->
            Text(
                text = horseItem.horseName,
                modifier = Modifier.clickable {
                    onHorseClick(horseItem.horseId)
                }
            )
        }
    }
}
