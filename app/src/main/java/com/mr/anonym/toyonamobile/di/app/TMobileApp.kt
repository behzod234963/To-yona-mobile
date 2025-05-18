package com.mr.anonym.toyonamobile.di.app

import android.app.Application
import com.google.crypto.tink.config.TinkConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TMobileApp :Application(){
    override fun onCreate() {
        super.onCreate()
        TinkConfig.register()
    }
}