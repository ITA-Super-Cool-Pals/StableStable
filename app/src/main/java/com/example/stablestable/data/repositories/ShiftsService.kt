package com.example.stablestable.data.repositories

import com.example.stablestable.data.classes.Shift

interface ShiftsService {

    suspend fun getCurrentWeekShifts(): List<Shift>
    suspend fun getCurrentShift(): Shift

    suspend fun addShift(data: Shift)
    suspend fun removeShift()

}
