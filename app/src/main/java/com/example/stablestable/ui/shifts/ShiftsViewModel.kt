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

class ShiftsViewModel: ViewModel() {

    var openShiftDialog by  mutableStateOf(false)

    val shiftsService: ShiftsServiceImpl = ShiftsServiceImpl()

    var viewedWeek: Int = 13
    var viewedDay: String = ""
    var viewedSegment: String = ""

    fun createShift(){
        val currentShift: Shift = Shift(viewedWeek,viewedDay,"John",viewedSegment)
        viewModelScope.launch {
            try {
                shiftsService.addShift(currentShift)
            } catch (e: Exception){
                Log.d(TAG, "Message: $e")
            }
        }
    }


}
