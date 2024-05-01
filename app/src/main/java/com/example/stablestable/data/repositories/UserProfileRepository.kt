package com.example.stablestable.data.repositories

import com.example.stablestable.data.UserService
import com.example.stablestable.data.classes.UserProfile
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.tasks.await
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.dataObjects

class UserProfileRepository(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
):UserService {

    override val currentUserId: String = auth.currentUser!!.uid

    override suspend fun getUser(userId: String): UserProfile? =
        firestore.collection("ryttere").document(userId).get().await().toObject<UserProfile>()

    override suspend fun getCurrentUser(): UserProfile? =
        firestore.collection("ryttere").document(currentUserId).get().await().toObject<UserProfile>()

}
