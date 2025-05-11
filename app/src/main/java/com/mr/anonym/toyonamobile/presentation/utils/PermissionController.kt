package com.mr.anonym.toyonamobile.presentation.utils

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.core.app.ActivityCompat
import com.mr.anonym.toyonamobile.presentation.constants.IO_REQUEST_CODE
import com.mr.anonym.toyonamobile.presentation.constants.NOTIFICATIONS_REQUEST_CODE

class PermissionController(private val context: Context) {

    fun checkReadContactsPermission(activity: Activity): Boolean {
        return ActivityCompat.shouldShowRequestPermissionRationale(
            activity,
            Manifest.permission.READ_CONTACTS
        )
    }

    @Composable
    fun ContactsPermissionController(
        context: Context,
        onGranted: () -> Unit
    ) {
        val permissionState = remember { mutableStateOf(false) }
        val permissionLauncher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { permissions ->
            permissionState.value = permissions
            if (permissionState.value) {
                onGranted()
                permissionState.value = false
            }
        }
        LaunchedEffect(permissionState.value) {
            if (
                ActivityCompat.checkSelfPermission(
                    context, Manifest.permission.READ_CONTACTS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionLauncher.launch(
                    Manifest.permission.READ_CONTACTS
                )
            } else {
                onGranted()
                permissionState.value = false
            }
        }
    }

    fun requestNotificationPermission(activity: Activity?) {
        if (ActivityCompat.checkSelfPermission(
                context, Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                activity?.let {
                    ActivityCompat.requestPermissions(
                        it,
                        arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                        NOTIFICATIONS_REQUEST_CODE
                    )
                }
            }
        }
    }

    fun requestExternalStoragePermission(activity: Activity) {
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,
                arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ),
                IO_REQUEST_CODE
            )
        }
    }
}