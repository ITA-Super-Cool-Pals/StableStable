package com.example.stablestable.data.repositories.impl

import com.example.stablestable.data.classes.UserProfile
import com.example.stablestable.data.repositories.AccountService
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import com.google.firebase.firestore.toObject

class AccountServiceImpl: AccountService {
    private val db: FirebaseFirestore = Firebase.firestore
    private val auth: FirebaseAuth = Firebase.auth

    override val currentUserId: String = auth.currentUser?.uid ?: ""

    override suspend fun getUser(userId: String): UserProfile? =
        db.collection("users").document(userId).get().await().toObject<UserProfile>()

    override suspend fun getCurrentUser(userId: String): UserProfile? =
        db.collection("users").document(userId).get().await().toObject<UserProfile>()

    override suspend fun getAllUsersInStable(stableId:String): List<UserProfile?> {
        val userDataList = mutableListOf<UserProfile>()
        val query = db.collection("users").whereEqualTo("stableId", stableId)

        val dataSnapshot = query.get().await()

        for (document in dataSnapshot.documents) {
            // populate return list with data
            val userData = document.toObject<UserProfile>()
            if (userData != null) {
                userDataList.add(userData)
            }
        }

        return userDataList
    }

    override suspend fun userCreate(user: UserProfile, password: String) {
        auth.createUserWithEmailAndPassword(user.email, password).await()
        val userDocRef = db.collection("users").document(currentUserId)
        userDocRef.set(user)
    }

    override suspend fun userLogin(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

}
