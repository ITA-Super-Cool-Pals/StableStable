package com.example.stablestable.data.repositories.impl.NotificationsFCM

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMNotificationService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Handle FCM message received here
        // This method is called when a message is received while the app is in the foreground
    }

    override fun onNewToken(token: String) {
        // Handle token refresh here
        // This method is called when a new token is generated or existing token is refreshed
    }
}
