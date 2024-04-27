package com.example.stablestable.data

data class UserProfileClass(
    val userID: String,
    val contactInfo: Map<String,Any>,
    val firstName: String,
    val lastName: String,
    val stableID: String
){
    val phone: String = contactInfo["phone"] as String
    val email: String = contactInfo["email"] as String
}
