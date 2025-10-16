package com.mr.anonym.toyonamobile.presentation.notifications

import android.Manifest
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.annotation.RequiresPermission
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NotificationReceiver: BroadcastReceiver() {

    @Inject
    lateinit var notificationManager : NotificationManager

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    override fun onReceive(context: Context, intent: Intent) {

        Log.d("ForegroundLogging", "NotificationReceiverOnReceive: broadcast is working")
        val notificationController = NotificationController(context, notificationManager)
        val title = intent.getStringExtra("title")
        val contentText = intent.getStringExtra("contentText")

        notificationController.showNotification(
            modelID = 0,
            title = title?:"",
            contentText = contentText?:""
        )
    }
}