package com.example.stablestable.data.repositories.impl

import com.example.stablestable.data.classes.Shift
import com.example.stablestable.data.repositories.ShiftsService
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await

class ShiftsServiceImpl: ShiftsService {
    private val db: FirebaseFirestore = Firebase.firestore


    override suspend fun getCurrentWeekShifts(): List<Shift> {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrentShift(): Shift {
        TODO("Not yet implemented")
    }

    override suspend fun addShift(data: Shift) {
        db.collection("shifts").document(data.shiftCode).set(data).await()
    }

    override suspend fun removeShift() {
        TODO("Not yet implemented")
    }

}
