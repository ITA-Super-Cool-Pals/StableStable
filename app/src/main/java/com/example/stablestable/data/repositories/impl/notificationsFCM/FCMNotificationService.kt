package com.example.stablestable.data.repositories.impl.notificationsFCM

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.stablestable.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FCMNotificationService : FirebaseMessagingService() {

    companion object{
        private const val TAG = "FCM notification"
        const val DEFAULT_NOTIFICATION_ID = 0
    }
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Handle FCM message received here
        // This method is called when a message is received while the app is in the foreground
        val title = remoteMessage.notification?.title
        val body = remoteMessage.notification?.body

        val notificationBuilder = NotificationCompat.Builder(applicationContext, "1")
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

// Create notification channel if API level is Oreo or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "1",
                "Default",
                NotificationManager.IMPORTANCE_HIGH
            )
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            channel.description = "Default notification channel"
            notificationManager.createNotificationChannel(channel)
        }

        notificationBuilder
            .setSmallIcon(R.drawable.bell_01)
            .setContentTitle(title)
            .setContentText(body)

        notificationManager.notify(DEFAULT_NOTIFICATION_ID, notificationBuilder.build())
    }

    override fun onNewToken(token: String) {
        // Handle token refresh here
        // This method is called when a new token is generated or existing token is refreshed
        Log.i(TAG, "new FCM token created: $token")
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        initNotificationChannel(notificationManager)
    }
    private fun initNotificationChannel(notificationManager: NotificationManager) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannelIfNotExists(
                channelId = "1",
                channelName = "Default"
            )
        }
    }

}
@RequiresApi(Build.VERSION_CODES.O)
fun NotificationManager.createNotificationChannelIfNotExists(
    channelId: String,
    channelName: String,
    importance: Int = NotificationManager.IMPORTANCE_HIGH
){
    var channel = this.getNotificationChannel(channelId)

    if (channel == null) {
        channel = NotificationChannel(
            channelId,
            channelName,
            importance
        )
        this.createNotificationChannel(channel)
    }
}
