package com.example.stablestable.data.repositories.impl

import com.example.stablestable.data.classes.Shift
import com.example.stablestable.data.classes.UserProfile
import com.example.stablestable.data.repositories.ShiftsService
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await

class ShiftsServiceImpl() : ShiftsService {
    private val db: FirebaseFirestore = Firebase.firestore

    override val shiftsWithFlow: Flow<List<Shift>>
        get() =
            db.collection("shifts").whereEqualTo("weekNumber",13).dataObjects()


    override suspend fun getCurrentWeekShifts(week:Int): List<Shift> {
        val shiftList = mutableListOf<Shift>()
        val query = db.collection("shifts").whereEqualTo("weekNumber", week)

        val dataSnapshot = query.get().await()

        for (document in dataSnapshot.documents) {
            // populate return list with data
            val shiftData = document.toObject<Shift>()
            if (shiftData != null) {
                shiftList.add(shiftData)
            }
        }

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
