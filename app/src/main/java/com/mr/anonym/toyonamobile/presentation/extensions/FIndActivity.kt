package com.mr.anonym.toyonamobile.presentation.extensions

import android.content.Context
import androidx.fragment.app.FragmentActivity

fun Context.findActivity(): FragmentActivity{
    return this as FragmentActivity
}