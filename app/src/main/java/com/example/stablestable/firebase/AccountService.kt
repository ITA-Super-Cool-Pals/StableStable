package com.example.stablestable.firebase

import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class AccountService {
    //authenticate creates a user with email and password.
    //remember to pass onResult and onElse
    fun authenticate(email: String, password: String, onResult: () -> Unit, onElse:()-> Unit) {
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                onResult()
            }
            .addOnFailureListener {
                onElse()
            }
    }

    fun login(email: String, password: String, onResult: () -> Unit) {
        Firebase.auth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //TODO: Skal der ske noget her?
            }
    }
}
