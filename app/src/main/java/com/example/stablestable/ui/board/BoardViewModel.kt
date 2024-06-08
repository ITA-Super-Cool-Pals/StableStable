package com.example.stablestable.ui.board

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stablestable.data.classes.BoardMessage
import com.example.stablestable.data.repositories.BoardMessagesService
import com.example.stablestable.data.repositories.impl.BoardMessagesServiceImpl
import com.example.stablestable.navigation.AuthViewModel
import kotlinx.coroutines.launch
//Lykke
class BoardViewModel: ViewModel() {
    private val authViewModel: AuthViewModel = AuthViewModel()
    private val boardMessagesService: BoardMessagesService = BoardMessagesServiceImpl()

    var inputFieldText = mutableStateOf("")

    private val userId: String
        get() = authViewModel.userId ?: ""

    private val stableId: String
        get() = authViewModel.currentUserProfile.value?.stableId ?: ""

    fun createBoardMessage() {
        val message = BoardMessage(
            userId = userId,
            content = inputFieldText.value,
            stableId = stableId
        )
        saveBoardMessage(message)
    }

    private fun saveBoardMessage(message: BoardMessage) {
        viewModelScope.launch {
            boardMessagesService.createBoardPostOnDB(message)
        }
    }

}
