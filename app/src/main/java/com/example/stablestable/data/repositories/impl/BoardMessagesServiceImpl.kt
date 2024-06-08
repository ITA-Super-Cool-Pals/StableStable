package com.example.stablestable.data.repositories.impl

import android.content.ContentValues
import android.util.Log
import com.example.stablestable.data.classes.BoardMessage
import com.example.stablestable.data.repositories.BoardMessagesService
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

//Lykke
class BoardMessagesServiceImpl: BoardMessagesService {
    private val db: FirebaseFirestore = Firebase.firestore

    override suspend fun createBoardPostOnDB(boardMessage: BoardMessage) {
        val boardMessagesDocRef = db.collection("boardMessages").document()
        boardMessagesDocRef.set(boardMessage)
    }

    override suspend fun getListOfBoardPosts() : Flow<List<BoardMessage>> {
        return callbackFlow {
            val dbSnapshot = db.collection("boardMessages")

            dbSnapshot.addSnapshotListener { value, error ->
                error?.let {
                    this.close(it)

                }
                value?.let {
                    // Check if value can be casted as Message
                    // if not, return empty list

                    // Check if messages is empty
                    if (value.size() < 1) {
                        this.trySend(emptyList())
                    }
                    try {
                        val data = value.toObjects(BoardMessage::class.java)
                        this.trySend(data)
                    } catch (e: Exception) {
                        val data = emptyList<BoardMessage>()
                        this.trySend(data)
                        Log.d(ContentValues.TAG, "Exception at shift collection: $e")
                    }
                }
            }
            awaitClose { this.cancel() }
        }
    }
}
