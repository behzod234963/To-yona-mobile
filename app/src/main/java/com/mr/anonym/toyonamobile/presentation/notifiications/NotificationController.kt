package com.mr.anonym.toyonamobile.presentation.notifiications

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import androidx.annotation.RequiresPermission
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import com.mr.anonym.toyonamobile.R

class NotificationController (
    private val context: Context,
    private val notificationManager: NotificationManager
){

    @RequiresPermission(Manifest.permission.POST_NOTIFICATIONS)
    fun showNotification(
        requestCode: Int,
        title: String,
        contentText: String
    ){

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            notificationManager.notify(
                requestCode,
                createNotification(
                    title = title,
                    contentText = contentText
                ).build()
            )
        }
    }

    private fun createNotification(
        title: String,
        contentText: String
    ): NotificationCompat.Builder{

        val notificationCriterion = NotificationCompat.Builder( context,"notification channel id" )
            .setSmallIcon(R.drawable.ic_more)
            .setContentTitle(title)
            .setContentText(contentText)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setAutoCancel(true)
        return notificationCriterion
    }
}