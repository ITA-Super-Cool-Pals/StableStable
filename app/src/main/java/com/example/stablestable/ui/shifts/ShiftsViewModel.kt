package com.example.stablestable.ui.shifts

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.ProvidedValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stablestable.data.classes.Shift
import com.example.stablestable.data.repositories.impl.AccountServiceImpl
import com.example.stablestable.data.repositories.impl.ShiftsServiceImpl
import com.example.stablestable.navigation.AuthViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale

class ShiftsViewModel(
    private val shiftsService: ShiftsServiceImpl = ShiftsServiceImpl(),
    private val authViewModel: AuthViewModel = AuthViewModel()
) : ViewModel() {

    init {
        val dateInit: LocalDate = LocalDate.now();
        val currentWeekOfYearInit: Int = dateInit.get(WeekFields.of(Locale.GERMAN).weekOfYear())
        getCurrentShifts(currentWeekOfYearInit)
    }

    var openShiftDialog by mutableStateOf(false)
    var dialogContentState by mutableStateOf("")



    private val date: LocalDate = LocalDate.now();
    private val currentWeekOfYear: Int = date.get(WeekFields.of(Locale.GERMAN).weekOfYear())
    var currentWeek by mutableIntStateOf(currentWeekOfYear)

    var viewedWeek: Int = currentWeek
    var viewedDay: Int = 0
    var viewedSegment: String = ""
    var viewedUser by mutableStateOf("")

    private val currentShift: Shift
        get() =
            Shift(viewedWeek, viewedDay, viewedUser, viewedSegment)


    fun getCurrentShifts(week:Int): Flow<List<Shift>>{
        return shiftsService.shiftsWithFlow
            .map { it.filter { item -> item.weekNumber == week } }

    }

    fun createShift() {
        viewModelScope.launch {
            try {
                authViewModel.currentUserProfile.collect { currentUser ->
                    if (currentUser != null) {
                        val currentUserName = currentUser.firstName
                        viewedUser = currentUser.firstName

                        shiftsService.addShift(
                            Shift(
                                viewedWeek,
                                viewedDay,
                                currentUserName,
                                viewedSegment
                            )
                        )

                    }
                }
            } catch (e: Exception) {
                Log.d(TAG, "Message: $e")
            }
        }
    }

    fun removeShift() {
        viewModelScope.launch {
            try {
                shiftsService.removeShift(currentShift.shiftCode)
                Log.d(TAG, "Message: Removed")
            } catch (e: Exception) {
                Log.d(TAG, "Message: $e")
            }
        }

    }
}
