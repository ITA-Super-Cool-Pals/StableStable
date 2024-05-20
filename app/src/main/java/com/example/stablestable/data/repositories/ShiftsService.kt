package com.example.stablestable.data.repositories

import com.example.stablestable.data.classes.Shift
import kotlinx.coroutines.flow.Flow

interface ShiftsService {

    val shiftsWithFlow: Flow<List<Shift>>

    suspend fun getCurrentWeekShifts(week:Int): List<Shift>



    suspend fun getCurrentShift(): Shift

    suspend fun addShift(data: Shift)
    suspend fun removeShift(shiftsId: String)

}
