package com.example.stablestable.data.repositories.impl

import com.example.stablestable.data.classes.HorseProfile
import com.example.stablestable.data.classes.UserProfile
import com.example.stablestable.data.repositories.AccountService
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import com.google.firebase.firestore.toObject

/*
 * Firebase Account and Database interaction
 * Code by Emily & Josef
 */

class AccountServiceImpl: AccountService {
    private val db: FirebaseFirestore = Firebase.firestore
    private val auth: FirebaseAuth = Firebase.auth

    // Get a specific user
    override suspend fun getUser(userId: String): UserProfile? =
        db.collection("users").document(userId).get().await().toObject<UserProfile>()

    // Get the currently logged in user
    override suspend fun getCurrentUser(userId: String): UserProfile? =
        db.collection("users").document(userId).get().await().toObject<UserProfile>()

    // Get all users matching a stable id
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

    // Add a horse to the database
    override suspend fun addHorse(horseProfile: HorseProfile) {
       val horseDocRef = db.collection("horses").document()
       horseDocRef.set(horseProfile)
    }

    override suspend fun getHorsesByOwnerId(ownerId: String): List<Pair<String, HorseProfile?>> {
        val horseDataList = mutableListOf<Pair<String, HorseProfile?>>()
        val query = db.collection("horses").whereEqualTo("ownerId", ownerId)

        val dataSnapshot = query.get().await()

        for (document in dataSnapshot.documents) {
            val horseData = document.toObject<HorseProfile>()
            if (horseData != null) {
                horseDataList.add(Pair(document.id, horseData))
            }
        }

        return horseDataList
    }

    // Get a specific horse by ID
    override suspend fun getHorseById(horseId: String): HorseProfile? =
        db.collection("horses").document(horseId).get().await().toObject<HorseProfile>()

    // Create a new user
    override suspend fun userCreate(user: UserProfile, password: String) {
        auth.createUserWithEmailAndPassword(user.email, password).await()
        val currentUserId = auth.currentUser?.uid
        val userDocRef = db.collection("users").document(currentUserId!!)
        userDocRef.set(user)
    }

    // Login a user
    override suspend fun userLogin(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).await()
    }

}
