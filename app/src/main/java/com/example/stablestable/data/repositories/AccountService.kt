package com.example.stablestable.data.repositories

import com.example.stablestable.data.classes.UserProfile

interface AccountService {
    val currentUserId: String
    suspend fun getUser(userId:String): UserProfile?
    suspend fun getCurrentUser(): UserProfile?
    suspend fun createUser(user:UserProfile,pwd:String)
    suspend fun login(email:String,pwd:String)
}
