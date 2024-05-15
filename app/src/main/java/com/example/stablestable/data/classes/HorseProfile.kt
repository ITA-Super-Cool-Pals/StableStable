package com.example.stablestable.data.classes

import com.google.firebase.Timestamp

data class HorseProfile(
    val ownerId: String = "",
    val stableId: String = "",
    val name: String = "",
    val breed: String = "",
    val sex: String = "",
    val age: Timestamp = Timestamp.now(),
)
