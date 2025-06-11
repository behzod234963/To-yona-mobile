package com.mr.anonym.toyonamobile.presentation.managers

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun openSupportBot(context: Context,bot: String){

    val telegramUrl = "https://t.me/$bot"
    val intent = Intent(Intent.ACTION_VIEW, telegramUrl.toUri())
    context.startActivity(intent)
}