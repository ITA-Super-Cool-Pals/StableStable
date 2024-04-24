package com.example.stablestable.firebase

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        // Handle incoming messages here
        Log.d(TAG, "Message received: ${message.data}")
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        // Handle token refresh or new token generation here
        Log.d(TAG, "Refreshed token: $token")
        // You can save the token to SharedPreferences or send it to your server
    }

    companion object {
        private const val TAG = "FirebaseMessaging"
    }
}
