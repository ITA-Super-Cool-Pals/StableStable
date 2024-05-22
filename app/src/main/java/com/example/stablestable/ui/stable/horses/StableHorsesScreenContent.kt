package com.example.stablestable.ui.stable.horses

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

/*
 * Code by Emily
 */

@Composable
fun StableHorsesScreenContent(
    paddingValues: PaddingValues,
    onHorseClick: (String) -> Unit
){

    val viewModel: StableHorsesViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        // Lazy Column for displaying the list of horses
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = viewModel.horseList,
                itemContent = { horseItem ->
                    StableHorseListItem(
                        horse = horseItem,
                        onHorseClick = onHorseClick
                    )
                }
            )
        }
    }
}
