package com.mr.anonym.toyonamobile.presentation.utils

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager

fun restartApp(context: Context) {
    val packageManager: PackageManager = context.packageManager
    val intent: Intent =
        packageManager.getLaunchIntentForPackage(context.packageName)!!
    val componentName: ComponentName = intent.component!!
    val restartIntent: Intent = Intent.makeRestartActivityTask(componentName)
    context.startActivity(restartIntent)
}