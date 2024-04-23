package com.example.stablestable.firebase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class AccountService {
    // Login User
    fun userLogin(email: String, password: String, onResult: () -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onResult()
            }
            .addOnFailureListener { exception ->
                // Handle login failure
                // Log error or show toast message
            }
    }

    // Create User
    fun userCreate(email: String, password: String, onResult: () -> Unit, onElse:()-> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onResult()
            }
            .addOnFailureListener {
                onElse()
            }
    }


}
