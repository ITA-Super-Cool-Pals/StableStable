package com.example.stablestable.data.classes

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

//Lykke
data class BoardMessage (
    val userId: String = "",
    val content: String = "",
    val stableId: String = "",
    var userName: String = "",
    @ServerTimestamp
    val timeStamp: Date? = null
)
