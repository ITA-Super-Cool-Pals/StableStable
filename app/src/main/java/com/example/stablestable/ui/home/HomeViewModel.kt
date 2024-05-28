package com.example.stablestable.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
//Lykke
class HomeViewModel : ViewModel() {
    var showNotificationDialog by mutableStateOf(false)
    private set

    fun setNotificationDialogStateToTrue() {
        showNotificationDialog = true
    }

    fun setNotificationDialogStateToFalse() {
        showNotificationDialog = false
    }
}


