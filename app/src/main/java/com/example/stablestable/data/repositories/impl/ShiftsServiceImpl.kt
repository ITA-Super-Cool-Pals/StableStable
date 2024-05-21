package com.example.stablestable.data.repositories.impl

import android.util.Log
import com.example.stablestable.data.classes.Shift
import com.example.stablestable.data.repositories.ShiftsService
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.dataObjects
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class ShiftsServiceImpl : ShiftsService {
    private val db: FirebaseFirestore = Firebase.firestore



    override fun shiftsWithFlow(): Flow<List<Shift>>{
        return callbackFlow {
            val dbSnapshot = db.collection("shifts")

            dbSnapshot.addSnapshotListener { value, error ->
                error?.let{
                    this.close(it)
                }
                value?.let{
                    val data = value.toObjects(Shift::class.java)
                    this.trySend(data)
                }
            }
            awaitClose { this.cancel()}
        }
    }




    override suspend fun getCurrentWeekShifts(week:Int): List<Shift> {
        val shiftList = mutableListOf<Shift>()
        //val query = db.collection("shifts").whereEqualTo("weekNumber", week)

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
