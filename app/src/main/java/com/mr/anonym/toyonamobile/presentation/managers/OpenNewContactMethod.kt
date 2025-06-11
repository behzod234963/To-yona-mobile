package com.mr.anonym.toyonamobile.presentation.managers

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable
fun OpenNewContactMethod(context: Context):ManagedActivityResultLauncher<Intent, ActivityResult> {

    val contactLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK){
//            Something do here
        }else{
//            Something do here
        }
    }
    return contactLauncher
}