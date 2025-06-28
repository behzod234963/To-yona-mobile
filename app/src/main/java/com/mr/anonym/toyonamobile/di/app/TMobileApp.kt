package com.mr.anonym.toyonamobile.di.app

import android.app.Application
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.chuckerteam.chucker.api.Chucker
import com.google.crypto.tink.config.TinkConfig
import com.mr.anonym.toyonamobile.presentation.workManager.PartyWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltAndroidApp
class TMobileApp :Application(), Configuration.Provider{

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        TinkConfig.register()
        val workerConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .build()
        val checkPartyWorkerRequest = PeriodicWorkRequestBuilder<PartyWorker>(
            repeatInterval = 15,
            repeatIntervalTimeUnit = TimeUnit.MINUTES
        ).setConstraints(workerConstraints).build()
        WorkManager.getInstance(this@TMobileApp).enqueueUniquePeriodicWork(
            "check_party_work",
            ExistingPeriodicWorkPolicy.KEEP,
            checkPartyWorkerRequest,
        )
        Chucker.getLaunchIntent(this)
    }
    override val workManagerConfiguration: Configuration
        get() = Configuration
            .Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(workerFactory)
            .build()
}