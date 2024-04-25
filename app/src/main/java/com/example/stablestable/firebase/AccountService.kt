package com.example.stablestable.firebase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class AccountService {
    // Create User
    fun userCreate(email: String, password: String, fullName: String, phone: String, onResult: () -> Unit, onFailure: () -> Unit) {
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

    // Add currently authenticated user to the database
    fun createUserInFirestore(fullName: String, phone: String) {
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
                "email" to currentUser.email,
                "fullname" to fullName,
                "phone" to phone
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

    // Fetch user data from Firebase
    fun fetchUserData(onSuccess: (String) -> Unit, onFailure: () -> Unit) {
        val db = Firebase.firestore
        val currentUser = Firebase.auth.currentUser

        // Check if user is authenticated
        if (currentUser != null) {
            // Get the user ID
            val userId = currentUser.uid
            // Get user data from database
            val userDocRef = db.collection("ryttere").document(userId)

            // Fetch the user data
            userDocRef.get()
                .addOnSuccessListener { document ->
                    // Check if document exists
                    if (document.exists()) {
                        // TODO: Add more data that can be fetched, once user profile data structure is decided
                        // TODO: Needs to be after proper user creation is made
                        val email = document.getString("email")
                        if (email != null) {
                            onSuccess(email)
                        } else {
                            onFailure()
                        }
                    } else {
                        onFailure()
                    }
                }
                .addOnFailureListener { exception ->
                    onFailure()
                }
        } else {
            onFailure()
        }
    }

    // Update user data in Firebase
    fun updateUserData(email: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        // TODO: Decide what user information should be updated, and how it should be updated
        // TODO: Separate functions? All in one, with all info at once?
        val db = Firebase.firestore
        val currentUser = Firebase.auth.currentUser

        // Check if current user is authenticated
        if (currentUser != null) {
            val userId = currentUser.uid
            val userDocRef = db.collection("ryttere").document(userId)

            // Update user data
            userDocRef.update("email", email)
                .addOnSuccessListener {
                    onSuccess()
                }
                .addOnFailureListener {
                    onFailure()
                }
        } else {
            onFailure()
        }
    }
}
