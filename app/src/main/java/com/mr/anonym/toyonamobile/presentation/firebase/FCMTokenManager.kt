package com.mr.anonym.toyonamobile.presentation.firebase

import android.util.Log
import com.google.firebase.messaging.FirebaseMessaging

fun fcmTokenManager() {

    FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
        if (task.isSuccessful){
            val token = task.result
            Log.d("FirebaseLogging", "fcmTokenManagerToken: $token")
        }else{
            Log.d("FirebaseLogging", "fcmTokenManagerToken: ${task.exception}")
        }
    }
}