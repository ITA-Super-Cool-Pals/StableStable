package com.example.stablestable.data.repositories.impl

import com.example.stablestable.data.classes.HorseFeed
import com.example.stablestable.data.classes.HorseProfile
import com.example.stablestable.data.classes.UserProfile
import com.example.stablestable.data.repositories.AccountService
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.tasks.await

/*
 * Firebase Account and Database interaction
 * Code by Emily & Josef
 */

class AccountServiceImpl : AccountService {
    private val db: FirebaseFirestore = Firebase.firestore
    private val auth: FirebaseAuth = Firebase.auth

    // Get a specific user by ID
    override suspend fun getUserById(userId: String): UserProfile? =
        db.collection("users").document(userId).get().await().toObject<UserProfile>()

    // Get the currently logged in user
    override suspend fun getCurrentUser(userId: String): UserProfile? =
        db.collection("users").document(userId).get().await().toObject<UserProfile>()

    // Get all users matching a stable id
    override suspend fun getAllUsersInStable(stableId: String): List<Pair<String, UserProfile?>> {
        val userDataList = mutableListOf<Pair<String, UserProfile?>>()
        val query = db.collection("users").whereEqualTo("stableId", stableId)

        val dataSnapshot = query.get().await()

        for (document in dataSnapshot.documents) {
            // populate return list with data
            val userData = document.toObject<UserProfile>()
            if (userData != null) {
                userDataList.add(Pair(document.id, userData))
            }
        }
        // Return list of users in the stable
        return userDataList
    }

    // Add a horse to the database
    override suspend fun addHorse(horseProfile: HorseProfile) {
        val horseDocRef = db.collection("horses").document()
        horseDocRef.set(horseProfile)
    }

    // Get all horses by owner id
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
        // Return list of horses belonging to the given owner
        return horseDataList
    }

    // Get a specific horse by ID
    override suspend fun getHorseById(horseId: String): HorseProfile? =
        db.collection("horses").document(horseId).get().await().toObject<HorseProfile>()

    // Get all horses in a stable
    override suspend fun getHorsesByStableId(stableId: String): List<Pair<String, HorseProfile?>> {
        val horseDataList = mutableListOf<Pair<String, HorseProfile?>>()
        val query = db.collection("horses").whereEqualTo("stableId", stableId)
        println("acc stable id: $stableId")
        val dataSnapshot = query.get().await()

        for (document in dataSnapshot.documents) {
            val horseData = document.toObject<HorseProfile>()
            if (horseData != null) {
                horseDataList.add(Pair(document.id, horseData))
            }
        }
        // Return list of horses belonging to the given stable
        return horseDataList
    }

    // Update a horse's feed information
    override suspend fun updateHorseFeed(horseId: String, horseFeed: HorseFeed) {
        val horseDocRef = db.collection("horses").document(horseId)
        horseDocRef.update("horseFeed", horseFeed)
    }

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
