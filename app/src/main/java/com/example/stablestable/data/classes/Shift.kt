package com.example.stablestable.data.classes

import java.time.LocalDate

data class Shift(
    val date: LocalDate,
    val dayOfWeek: String = "",
    // TODO: Vi skal
    val user: String = "",
    // Morning/Evening
    val shiftTime: String = ""
)
