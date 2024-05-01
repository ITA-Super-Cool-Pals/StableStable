package com.example.stablestable.data

import com.example.stablestable.data.classes.UserProfile

interface UserService {

    val currentUserId: String

    suspend fun getUser(userId:String): UserProfile?
    suspend fun getCurrentUser(): UserProfile?
}
