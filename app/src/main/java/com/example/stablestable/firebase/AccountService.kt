package com.example.stablestable.firebase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class AccountService {
    // Create User
    fun userCreate(email: String, password: String, onResult: () -> Unit, onFailure: () -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onResult()
            }
            .addOnFailureListener {
                onFailure()
            }
    }

    // Login User
    fun userLogin(email: String, password: String, onResult: () -> Unit, onFailure: () -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onResult()
            }
            .addOnFailureListener {
                onFailure()
            }
    }

    fun createUserInFirestore(email: String) {
        // Firestore Database Access
        val db = Firebase.firestore

        // Get the current authenticated userID
        val currentUser = Firebase.auth.currentUser

        // Check if the user is authenticated
        if (currentUser != null) {
            // Get the user ID
            val userId = currentUser.uid

            // Create a reference to the user document using the UID
            val userDocRef = db.collection("ryttere").document(userId)

            // Add user data to Firestore
            val userData = hashMapOf(
                "email" to currentUser.email
                // TODO: Add more data fields for account creation
            )

            // Set the user document
            userDocRef.set(userData)
                .addOnSuccessListener {

                }
                .addOnFailureListener { err ->

                }
        } else {
            // User not authenticated
            // Handle accordingly
        }
    }
}
