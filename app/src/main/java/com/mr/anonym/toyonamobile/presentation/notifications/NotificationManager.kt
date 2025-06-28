package com.mr.anonym.toyonamobile.presentation.notifications

import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.core.net.toUri

fun notificationManager(context: Context) {

    val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
            putExtra(Settings.EXTRA_APP_PACKAGE,context.packageName)
        }
    }else{
        Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = "package:${context.packageName}".toUri()
        }
    }
    context.startActivity(intent)
}