package com.example.stablestable.ui.board

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stablestable.data.classes.BoardMessage
import com.example.stablestable.data.classes.UserProfile
import com.example.stablestable.data.repositories.AccountService
import com.example.stablestable.data.repositories.BoardMessagesService
import com.example.stablestable.data.repositories.impl.AccountServiceImpl
import com.example.stablestable.data.repositories.impl.BoardMessagesServiceImpl
import com.example.stablestable.navigation.AuthViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

//Lykke
class BoardViewModel: ViewModel() {
    private val authViewModel: AuthViewModel = AuthViewModel()
    private val boardMessagesService: BoardMessagesService = BoardMessagesServiceImpl()
    private val accountService: AccountService = AccountServiceImpl()


    //Waits for currentUser to be retrieved and created in AuthViewModel
    //before using currentUser´s stableId in this viewModel
    init {
        viewModelScope.launch {
            authViewModel.currentUserProfile.collect { currentUser ->
                if (currentUser != null) {
                    stableId = currentUser.stableId
                    getCurrentBoardMessages()
                    fetchUserProfile(userId)
                }
            }
        }
    }

    var inputFieldText = mutableStateOf("")

    private var userName: String = ""

    val userId: String
        get() = authViewModel.userId ?: ""

    private var stableId: String? = null

    private val _boardMessages = MutableStateFlow<List<BoardMessage>>(emptyList())
    val boardMessages: StateFlow<List<BoardMessage>>
        get() = _boardMessages

    private val userProfile = MutableStateFlow<UserProfile?>(null)

    fun createBoardMessage() {
        stableId?.let {
            val message = BoardMessage(
                userId = userId,
                content = inputFieldText.value,
                userName = userName,
                stableId = it
            )
            saveBoardMessage(message)
        } ?: run {
            // Handle the case where stableId is null
            println("Stable ID is null, cannot create message")
        }
    }

    private fun saveBoardMessage(message: BoardMessage) {
        viewModelScope.launch {
            boardMessagesService.createBoardPostOnDB(message)
        }
    }

    fun getCurrentBoardMessages() {
        stableId?.let { id ->
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    boardMessagesService.getListOfBoardPostsByStableId(id).collect { list ->
                        val sortedList = list.sortedByDescending { it.timeStamp }
                        _boardMessages.value = sortedList
                    }
                }
            }
        } ?: run {
            // Handle the case where stableId is null
            println("Stable ID is null, cannot fetch messages")
        }
    }
    private fun fetchUserProfile(userId: String) {
        viewModelScope.launch {
            val profile = accountService.getUserById(userId)
            userProfile.value = profile
            if (profile != null) {
                userName = profile.firstName + " " + profile.lastName
            }
            println("user profile: $profile")
        }
    }

}
