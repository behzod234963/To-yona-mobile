package com.mr.anonym.toyonamobile.presentation.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat

class PermissionController(private val context: Context) {

    fun requestNotificationPermission(activity: Activity?){
        if (ActivityCompat.checkSelfPermission(
            context, Manifest.permission.POST_NOTIFICATIONS
        ) != PackageManager.PERMISSION_GRANTED
        ){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                activity?.let {
                    ActivityCompat.requestPermissions(
                        it,
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS ),
                        0
                    )
                }
            }
        }
    }
}