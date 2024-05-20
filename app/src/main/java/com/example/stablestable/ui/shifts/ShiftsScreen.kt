package com.example.stablestable.ui.shifts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.components.CreateScaffold
import com.example.stablestable.data.classes.Shift

@Composable
fun ShiftsScreen(
    goToHome: () -> Unit,
    goToRiders: () -> Unit,
    goToShifts: () -> Unit,
    goToHorses: () -> Unit,
    viewModel: ShiftsViewModel = viewModel<ShiftsViewModel>()
) {
    val shiftsWithFlowState = viewModel.getCurrentShifts(viewModel.currentWeek).collectAsState(emptyList())

    CreateScaffold(
        content = {paddingValues ->
            ShiftsScreenContent(
                paddingValues,
                week = viewModel.currentWeek,
                shifts = shiftsWithFlowState.value,
                onNextWeek = {
                    viewModel.currentWeek ++
                    viewModel.viewedWeek = viewModel.currentWeek },
                onPreviousWeek = {
                    viewModel.currentWeek --
                    viewModel.viewedWeek = viewModel.currentWeek },
                onBoxOneClick = { s: String, s1: String, s2: String, sh: Shift? ->
                    viewModel.viewedDay = s
                    viewModel.viewedSegment = s1
                    viewModel.viewedUser = s2
                    viewModel.openShiftDialog = true
                    viewModel.dialogContentState = if (sh != null) {
                        "full"
                    } else {
                        "empty"
                    }
                })
        },
        goToHome = goToHome,
        goToRiders = goToRiders,
        goToShifts = goToShifts,
        goToHorses = goToHorses

    )

    // Opening and closing the dialog
    when {
        viewModel.openShiftDialog -> {
            ShiftsSingleDayDialog(displayedDay = viewModel.viewedDay,
                currentShiftName = viewModel.viewedUser,
                dialog = viewModel.dialogContentState,
                onDismissRequest = { viewModel.openShiftDialog = false },
                onAddMeClick = {
                    viewModel.createShift()
                    viewModel.dialogContentState = "full"
                },
                onRemoveMeClick = {
                    viewModel.removeShift()
                    viewModel.dialogContentState = "empty"
                })
        }
    }
}


