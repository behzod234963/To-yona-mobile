package com.mr.anonym.toyonamobile.di.module

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.mr.anonym.toyonamobile.presentation.managers.LocaleConfigurations
import com.mr.anonym.toyonamobile.presentation.managers.PermissionController
import com.mr.anonym.toyonamobile.presentation.notifiications.NotificationController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideLocaleConfigurations(@ApplicationContext context: Context): LocaleConfigurations =
        LocaleConfigurations(context)

    @Provides
    @Singleton
    fun providePermissionController(@ApplicationContext context: Context): PermissionController =
        PermissionController(context)

    @RequiresApi(Build.VERSION_CODES.O)
    @Provides
    fun provideNotificationManager(@ApplicationContext context: Context): NotificationManager{
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannel = NotificationChannel(
             "notification channel id",
             "Global notification channel",
             NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(notificationChannel)
        return notificationManager
    }

    @Provides
    @Singleton
    fun provideNotificationController(@ApplicationContext context: Context,notificationManager: NotificationManager): NotificationController =
        NotificationController(context, notificationManager)
}