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

    // Add currently authenticated user to the database
    fun createUserInFirestore(firstName: String, lastName: String, phone: String) {
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
                "firstname" to firstName,
                "lastname" to lastName,
                "contact-information" to hashMapOf(
                    "email" to currentUser.email,
                    "phone" to phone
                ),
                "stable-id" to "69"
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
    fun fetchUserData(onSuccess: (Map<String, Any>) -> Unit, onFailure: () -> Unit) {
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
                        // Convert document data to a Map
                        val userData = document.data
                        if (userData != null) {
                            onSuccess(userData)
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

    // Fetch all user data based on logged in users stable
    fun fetchAllUserData(onSuccess: (List<Map<String, Any>>) -> Unit, onFailure: () -> Unit) {
        val db = Firebase.firestore
        val currentUser = Firebase.auth.currentUser
        var stableId: String

        if (currentUser != null) {
            // Get the users current stable id
            val userId = currentUser.uid
            db.collection("ryttere").document(userId).get()
                .addOnSuccessListener { collection ->
                    if (collection.exists()) {
                        // Set the stable ID
                        stableId = collection.getString("stable-id").toString()

                        // Get reference to the "ryttere" collection
                        val userCollectionRef = db.collection("ryttere")

                        // Query to filter documents based on stable ID
                        val query = userCollectionRef.whereEqualTo("stable-id", stableId)

                        // Fetch documents based on query
                        query.get()
                            .addOnSuccessListener { querySnapshot ->
                                val userDataList = mutableListOf<Map<String, Any>>()

                                // Iterate over each document in the query result
                                for (document in querySnapshot.documents) {
                                    // Convert docuument data to a Map and add it to the list
                                    val userData = document.data
                                    if (userData != null) {
                                        userDataList.add(userData)
                                    }
                                }
                                // Pass the list of user data to the success callback
                                onSuccess(userDataList)
                            }
                            .addOnFailureListener { exception ->
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
