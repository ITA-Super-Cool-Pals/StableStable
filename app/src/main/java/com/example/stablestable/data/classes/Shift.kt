package com.example.stablestable.data.classes


data class Shift(
    // kode på uge/dag/time, fx. 13wedMORN eller 43tueEVEN

    val weekNumber: Int = 0,
    val dayOfWeek: Int = 0,
    val user: String = "",
    val userId: String = "",
    // Morning/Evening
    val shiftTime: String = ""
)
{
    val shiftCode: String
        get() = "${this.weekNumber}${this.dayOfWeek}${this.shiftTime}"

}
