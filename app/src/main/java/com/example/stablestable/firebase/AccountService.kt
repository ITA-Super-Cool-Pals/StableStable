package com.example.stablestable.firebase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth

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
}
