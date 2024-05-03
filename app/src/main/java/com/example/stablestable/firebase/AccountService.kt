package com.example.stablestable.firebase

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject

/*
 * Firebase Account and Database integration
 * Code by Emily
 */

class AccountService {
    // Create User
    fun userCreate(email: String, password: String, onResult: () -> Unit, onFailure: (String) -> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onResult()
            }
            .addOnFailureListener { exception ->
                onFailure(exception.message?: "Account creation failed: Unknown error")
            }
    }

    // Login User
    fun userLogin(email: String, password: String, onResult: () -> Unit, onFailure: (String) -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onResult()
            }
            .addOnFailureListener { exception ->
                onFailure(exception.message?: "Login failed: Unknown error")
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
                .addOnSuccessListener {}
                .addOnFailureListener {}
        } else {
            Log.e("CreateUserInFirestore", "User is not authenticated")
        }
    }

    // Fetch user data from Firebase
    fun fetchUserData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (String) -> Unit) {
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
                        Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                        val userData = document.data
                        if (userData != null) {
                            onSuccess(userData)
                        } else {
                            onFailure("ERROR: User data does not exist")
                            Log.e("FetchUserData", "User data does not exist")
                        }
                    } else {
                        onFailure("ERROR: User document does not exist")
                        Log.e("FetchUserData", "User document does not exist")
                    }
                }
                .addOnFailureListener { exception ->
                    onFailure(exception.message?: "ERROR: Failed to get user data: Unknown error")
                }
        } else {
            onFailure("ERROR: User not authenticated")
            Log.e("FetchUserData", "User not authenticated")
        }
    }

    // Fetch all user data based on logged in users stable
    fun fetchAllUserData(onSuccess: (List<Map<String, Any>>) -> Unit, onFailure: (String) -> Unit) {
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
                                onFailure(exception.message?: "Could not query documents, unknown error")
                            }
                    } else {
                        onFailure("ERROR: Collection does not exist")
                        Log.e("fetchAllUserData", "Collection does not exist")
                    }
                }
                .addOnFailureListener { exception ->
                    onFailure(exception.message?: "Could not get collection, unknown error")
                }
        } else {
            onFailure("ERROR: User not authenticated")
            Log.e("FetchAllUserData", "User not authenticated")
        }
    }

    // Update user data in Firebase
    fun updateUserData(email: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
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
                .addOnFailureListener { exception ->
                    onFailure(exception.message?: "Failed to update user data, unknown error")
                }
        } else {
            onFailure("ERROR: Could not authenticate user")
            Log.e("updateUserData", "Could not authenticate user")
        }
    }
}
