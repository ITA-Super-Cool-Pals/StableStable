package com.example.stablestable.firebase

import android.content.ContentValues.TAG
import android.util.Log
import com.example.stablestable.data.UserProfileClass
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

        if (currentUser == null) {
            Log.e("CreateUserInFirestore", "User is not authenticated")
            return
        }

        // Get the user ID
        val userId = currentUser.uid

        // Create a reference to the user document using the UID
        val userDocRef = db.collection("ryttere").document(userId)

        // Add user data to Firestore
        val userData = UserProfileClass(
            firstName = firstName,
            lastName = lastName,
            email = currentUser.email,
            phone = phone
        )

        // Set the user document
        userDocRef.set(userData)
            .addOnSuccessListener {}
            .addOnFailureListener {}
    }

    // Fetch user data from Firebase
    fun fetchUserData(onSuccess: (Map<String, Any>) -> Unit, onFailure: (String) -> Unit) {
        val db = Firebase.firestore
        val currentUser = Firebase.auth.currentUser

        if (currentUser == null) {
            onFailure("ERROR: User not authenticated")
            Log.e("FetchUserData", "User not authenticated")
            return
        }

        // Get the user ID
        val userId = currentUser.uid
        // Get user data from database
        val userDocRef = db.collection("ryttere").document(userId)
        // Fetch the user data
        userDocRef.get()
            .addOnSuccessListener { document ->
                // Convert document data to a Map
                Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                val userData = document.data

                // If user data is not null, fetch user data
                if (userData != null) {
                    onSuccess(userData)
                }
            }
            .addOnFailureListener { exception ->
                onFailure(exception.message?: "ERROR: Failed to get user data: Unknown error")
            }
    }

    // Fetch all user data based on logged in users stable
    fun fetchAllUserData(onSuccess: (List<Map<String, Any>>) -> Unit, onFailure: (String) -> Unit) {
        val db = Firebase.firestore
        val currentUser = Firebase.auth.currentUser
        var stableId: String

        if (currentUser == null) {
            onFailure("ERROR: User not authenticated")
            Log.e("FetchAllUserData", "User not authenticated")
            return
        }

        // Get current authenticated users id
        val userId = currentUser.uid
        db.collection("ryttere").document(userId).get()
            .addOnSuccessListener { collection ->
                // Get stable ID
                stableId = collection.getString("stableId").toString()

                // Get reference to the "ryttere" collection
                val userCollectionRef = db.collection("ryttere")

                // Query to filter documents based on stable ID
                val query = userCollectionRef.whereEqualTo("stableId", stableId)

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
                        onFailure(exception.message ?: "Could not query documents, unknown error")
                    }
            }
            .addOnFailureListener { exception ->
                onFailure(exception.message ?: "Could not get collection, unknown error")
            }
    }

    // Update user data in Firebase
    fun updateUserData(email: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        // TODO: Decide what user information should be updated, and how it should be updated
        // TODO: Separate functions? All in one, with all info at once?
        val db = Firebase.firestore
        val currentUser = Firebase.auth.currentUser

        if (currentUser == null) {
            onFailure("ERROR: Could not authenticate user")
            Log.e("updateUserData", "Could not authenticate user")
            return
        }

        // Get current authenticated users id
        val userId = currentUser.uid
        // get reference to the users document in firebase database
        val userDocRef = db.collection("ryttere").document(userId)

        // Update the users data
        // TODO: Update to follow new data class structure
        userDocRef.update("email", email)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { exception ->
                onFailure(exception.message?: "Failed to update user data, unknown error")
            }
    }
}
