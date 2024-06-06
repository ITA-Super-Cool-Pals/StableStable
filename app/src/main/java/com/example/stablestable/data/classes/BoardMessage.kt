package com.example.stablestable.data.classes

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

//Lykke
class BoardMessage {
    val userId: String = ""
    val content: String = ""
    @ServerTimestamp
    val timeStamp: Date? = null
}
