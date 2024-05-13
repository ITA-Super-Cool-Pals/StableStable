package com.example.stablestable.data.repositories

import com.example.stablestable.data.classes.HorseProfile
import com.example.stablestable.data.classes.UserProfile

interface AccountService {
    suspend fun getUser(userId: String): UserProfile?
    suspend fun getCurrentUser(userId: String): UserProfile?
    suspend fun getAllUsersInStable(stableId: String): List<UserProfile?>

    suspend fun addHorse(horseProfile: HorseProfile)
    suspend fun getHorsesByOwnerId(ownerId: String): List<HorseProfile?>
    suspend fun userCreate(user: UserProfile, password: String)
    suspend fun userLogin(email: String, password: String)

}
