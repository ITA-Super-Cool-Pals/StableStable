package com.example.stablestable.ui.shifts

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stablestable.data.classes.Shift
import com.example.stablestable.data.repositories.impl.ShiftsServiceImpl
import com.example.stablestable.navigation.AuthViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale

class ShiftsViewModel(
    private val shiftsService: ShiftsServiceImpl = ShiftsServiceImpl(),
    private val authViewModel: AuthViewModel = AuthViewModel()
) : ViewModel() {

    init {
        val dateInit: LocalDate = LocalDate.now()
        val currentWeekOfYearInit: Int = dateInit.get(WeekFields.of(Locale.GERMAN).weekOfYear())
        getCurrentShifts(currentWeekOfYearInit)
    }

    var openShiftDialog by mutableStateOf(false)
    var dialogContentState by mutableStateOf("")
    var isMyShift by mutableStateOf(true)


    private val date: LocalDate = LocalDate.now()
    private val currentWeekOfYear: Int = date.get(WeekFields.of(Locale.GERMAN).weekOfYear())
    var currentWeek by mutableIntStateOf(currentWeekOfYear)


    val currentUserId = authViewModel.userId?:""
    var viewedWeek: Int = currentWeek
    var viewedDay: Int = 0
    var viewedSegment: String = ""
    var viewedUser by mutableStateOf("")
    var viewedUserId = ""







    fun checkCurrentUser(id: String){

        isMyShift = currentUserId == id

    }





    private val currentShift: Shift
        get() =
            Shift(viewedWeek, viewedDay, viewedUser,viewedUserId, viewedSegment)


    private val _shifts = MutableStateFlow<List<Shift>>(emptyList())
    val shifts: StateFlow<List<Shift>>
        get() = _shifts

    fun getCurrentShifts(week:Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                shiftsService.shiftsWithFlow().collect { list ->
                    _shifts.value = list.filter { it.weekNumber == week }
                }
            }
        }
    }

    fun createShift() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    authViewModel.currentUserProfile.collect { currentUser ->
                        if (currentUser != null) {

                            viewedUser = currentUser.firstName

                            val currentUserId = authViewModel.userId ?: ""
                            viewedUserId = currentUserId

                            shiftsService.addShift(
                                Shift(
                                    viewedWeek,
                                    viewedDay,
                                    viewedUser,
                                    viewedUserId,
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
