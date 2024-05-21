package com.example.stablestable.ui.shifts

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.stablestable.R
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
    val shiftsWithFlowState = viewModel.getCurrentShifts(viewModel.currentWeek)

    CreateScaffold(
        content = {paddingValues ->
            viewModel.shifts.value?.let {
                ShiftsScreenContent(
                    paddingValues,
                    week = viewModel.currentWeek,
                    shifts = it,
                    onNextWeek = {
                        viewModel.currentWeek ++
                        viewModel.viewedWeek = viewModel.currentWeek },
                    onPreviousWeek = {
                        viewModel.currentWeek --
                        viewModel.viewedWeek = viewModel.currentWeek },
                    onShiftsBoxClick = { i: String, s: String, s1: String, sh: Shift? ->
                        viewModel.viewedDay = i
                        viewModel.viewedSegment = s
                        viewModel.viewedUser = s1
                        viewModel.openShiftDialog = true
                        viewModel.dialogContentState = if (sh != null) {
                            "full"
                        } else {
                            "empty"
                        }
                    })
            }
        },
        goToHome = goToHome,
        goToRiders = goToRiders,
        goToShifts = goToShifts,
        goToHorses = goToHorses,
        screen = stringResource(R.string.shifts)

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


