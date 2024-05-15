package com.example.stablestable.data.classes

data class HorseProfile(
    val ownerId: String = "",
    val stableId: String = "",
    val name: String = "",
    val breed: String = "",
    val sex: String = "",
    val age: com.google.firebase.Timestamp = com.google.firebase.Timestamp.now()
)
