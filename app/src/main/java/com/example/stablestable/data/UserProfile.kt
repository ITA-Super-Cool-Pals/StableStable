package com.example.stablestable.data

data class UserProfileClass(
    val firstName: String,
    val lastName: String,
    val email: String?,
    val phone: String,
    val stableId: String = "0"
)
