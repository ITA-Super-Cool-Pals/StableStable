package com.example.stablestable.data.repositories

import com.example.stablestable.data.classes.BoardMessage
import com.example.stablestable.data.classes.Shift
import kotlinx.coroutines.flow.Flow

//Lykke
interface BoardMessagesService {
    suspend fun createBoardPost(boardMessage: BoardMessage)
    suspend fun getListOfBoardPosts() : Flow<List<BoardMessage>>

}
