package com.example.stablestable.data.repositories

import com.example.stablestable.data.classes.BoardMessage
import kotlinx.coroutines.flow.Flow

//Lykke
interface BoardMessagesService {
    suspend fun createBoardPostOnDB(boardMessage: BoardMessage)
    suspend fun getListOfBoardPostsByStableId(stableId: String) : Flow<List<BoardMessage>>

}
