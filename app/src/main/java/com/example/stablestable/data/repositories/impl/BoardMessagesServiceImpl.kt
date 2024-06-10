package com.example.stablestable.data.repositories.impl


import com.example.stablestable.data.classes.BoardMessage
import com.example.stablestable.data.repositories.BoardMessagesService
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await

//Lykke
class BoardMessagesServiceImpl: BoardMessagesService {
    private val db: FirebaseFirestore = Firebase.firestore

    override suspend fun createBoardPostOnDB(boardMessage: BoardMessage) {
        val boardMessagesDocRef = db.collection("boardMessages").document()
        boardMessagesDocRef.set(boardMessage).await()
    }

    override suspend fun getListOfBoardPostsByStableId(stableId: String) : Flow<List<BoardMessage>> {
        return callbackFlow {
            val dbSnapshot = db.collection("boardMessages").whereEqualTo("stableId", stableId)

            val listenerRegistration = dbSnapshot.addSnapshotListener { value, error ->
                error?.let {
                    this.close(it)
                }
                value?.let {
                    try {
                        val data = it.toObjects(BoardMessage::class.java)
                        this.trySend(data)
                    } catch (e: Exception) {
                        this.trySend(emptyList())
                    }
                }
            }
            awaitClose { listenerRegistration.remove() }
        }
    }
}
