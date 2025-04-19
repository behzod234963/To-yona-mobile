package com.mr.anonym.toyonamobile.presentation.notifiications

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.core.content.ContextCompat.startActivity
import androidx.core.net.toUri
import dagger.hilt.android.internal.Contexts

fun notificationController(context: Context) {

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