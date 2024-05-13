package com.example.stablestable.data.repositories

import com.example.stablestable.data.classes.UserProfile

interface AccountService {
    val currentUserId: String?

    suspend fun getUser(userId: String): UserProfile?
    suspend fun getCurrentUser(userId: String): UserProfile?
    suspend fun getAllUsersInStable(stableId:String): List<UserProfile?>
    suspend fun userCreate(user: UserProfile, password: String)
    suspend fun userLogin(email: String, password: String)

}
