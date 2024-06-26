package com.example.stablestable.data.repositories

import com.example.stablestable.data.classes.HorseFeed
import com.example.stablestable.data.classes.HorseProfile
import com.example.stablestable.data.classes.UserProfile

/*
    Filen er skrevet af Josef

 */

interface AccountService {
    suspend fun getUserById(userId: String): UserProfile?
    suspend fun getCurrentUser(userId: String): UserProfile?
    suspend fun getAllUsersInStable(stableId: String): List<Pair<String, UserProfile?>>
    suspend fun addHorse(horseProfile: HorseProfile)
    suspend fun getHorsesByOwnerId(ownerId: String): List<Pair<String, HorseProfile?>>
    suspend fun getHorsesByStableId(stableId: String): List<Pair<String, HorseProfile?>>
    suspend fun getHorseById(horseId: String): HorseProfile?
    suspend fun updateHorseFeed(horseId: String, horseFeed: HorseFeed)
    suspend fun userCreate(user: UserProfile, password: String)
    suspend fun userLogin(email: String, password: String)

}
