package com.example.stablestable.ui.shifts

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stablestable.data.classes.Shift
import com.example.stablestable.data.repositories.impl.ShiftsServiceImpl
import kotlinx.coroutines.launch

class ShiftsViewModel(
    private val shiftsService: ShiftsServiceImpl = ShiftsServiceImpl()
): ViewModel() {

    var openShiftDialog by  mutableStateOf(false)

    val shiftsWithFlow = shiftsService.shiftsWithFlow


    var viewedWeek: Int = 13
    var viewedDay: String = ""
    var viewedSegment: String = ""

    var currentShift: Shift = Shift(viewedWeek,viewedDay,"John",viewedSegment)



    fun createShift(){
        viewModelScope.launch {
            val currentShift1: Shift = Shift(viewedWeek,viewedDay,"John",viewedSegment)
            try {
                shiftsService.addShift(currentShift1)
            } catch (e: Exception){
                Log.d(TAG, "Message: $e")
            }
        }
    }

    fun removeShift(){
        viewModelScope.launch {
            try {
                shiftsService.removeShift(currentShift.shiftCode)
            } catch (e: Exception){
                Log.d(TAG, "Message: $e")
            }
        }

    }

    fun getAllShifts(){

    }




}
