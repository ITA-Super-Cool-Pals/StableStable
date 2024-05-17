package com.example.stablestable.data.repositories.impl

import com.example.stablestable.data.classes.Shift
import com.example.stablestable.data.classes.UserProfile
import com.example.stablestable.data.repositories.ShiftsService
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.dataObjects
//import com.google.firebase.firestore.ktx.dataObjects
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class ShiftsServiceImpl : ShiftsService {
    private val db: FirebaseFirestore = Firebase.firestore


    override val shiftsWithFlow: Flow<List<Shift>>
        get() =
            db.collection("shifts").dataObjects()



    override suspend fun getCurrentWeekShifts(week:Int): List<Shift> {
        val shiftList = mutableListOf<Shift>()
        val query = db.collection("shifts").whereEqualTo("weekNumber", week)

        return shiftList.toList()
    }


    override suspend fun getCurrentShift(): Shift {
        TODO("Not yet implemented")
    }

    override suspend fun addShift(data: Shift) {
        db.collection("shifts").document(data.shiftCode).set(data).await()
    }

    override suspend fun removeShift(shiftId: String) {
        db.collection("shifts").document(shiftId).delete().await()
    }

}
