package com.mr.anonym.toyonamobile.presentation.firebase

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.mr.anonym.toyonamobile.presentation.notifications.NotificationController

class FirebaseMessagingServiceManager(
//    private val notificationController: NotificationController
): FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.notification?.let {
//            Log.d("FirebaseLogging", "FMSMessageReceived: ${it.body}")
//            notificationController.showNotification(
//                modelID = System.currentTimeMillis().toInt(),
//                title = it.title?:"",
//                contentText = it.body?:""
//            )
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FirebaseLogging", "FMSNewToken: $token")
    }
}