package com.example.stablestable.data.classes

import java.time.LocalDate

data class Shift(
    // kode på uge/dag/time, fx. 13wedMORN eller 43tueEVEN

    val weekNumber: Int = 0,
    val dayOfWeek: String = "",
    // TODO: Vi skal
    val user: String = "",
    // Morning/Evening
    val shiftTime: String = ""
)
{
    val shiftCode: String
        get() = "${this.weekNumber}${this.dayOfWeek}${this.shiftTime}"

}
