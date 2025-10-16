package com.mr.anonym.toyonamobile.presentation.notifications

import android.Manifest
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.mr.anonym.toyonamobile.R
import com.mr.anonym.toyonamobile.presentation.utils.NOTIFICATION_CONTENT_INTENT_REQUEST_CODE
import com.mr.anonym.toyonamobile.ui.activity.MainActivity

class NotificationController(
    private val context: Context,
    private val notificationManager: NotificationManager
) {

    fun showNotification(
        modelID:Int,
        title: String,
        contentText: String
    ) {

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val flag = PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE
            val intent = Intent(context, MainActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(
                context,
                NOTIFICATION_CONTENT_INTENT_REQUEST_CODE,
                intent,
                flag
            )
            val notification = NotificationCompat.Builder(context, "notification channel id")
                .setSmallIcon(R.drawable.ic_more)
                .setContentTitle(title)
                .setContentText(contentText)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .build()

            notificationManager.notify(modelID, notification)
        }
    }
}