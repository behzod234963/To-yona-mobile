package com.mr.anonym.toyonamobile.di.module

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.mr.anonym.data.implementations.MonitoringRepositoryImpl
import com.mr.anonym.data.implementations.NotificationsRepositoryImpl
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.data.instance.local.room.MonitoringDAO
import com.mr.anonym.data.instance.local.room.NotificationsDAO
import com.mr.anonym.data.instance.local.room.RoomInstance
import com.mr.anonym.domain.repository.MonitoringRepository
import com.mr.anonym.domain.repository.NotificationsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideNotificationRepository(notificationsDAO: NotificationsDAO): NotificationsRepository = NotificationsRepositoryImpl(notificationsDAO)

    @Provides
    @Singleton
    fun provideMonitoringRepository(monitoringDAO: MonitoringDAO): MonitoringRepository =
        MonitoringRepositoryImpl(monitoringDAO)

    @Provides
    @Singleton
    fun provideDataStoreInstance(@ApplicationContext context: Context): DataStoreInstance =
        DataStoreInstance(context)
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferencesInstance =
        SharedPreferencesInstance(context)

    @Provides
    @Singleton
    fun provideRoomInstance(application: Application): RoomInstance =
        Room.databaseBuilder(
            context = application,
            klass = RoomInstance::class.java,
            name = RoomInstance.DB_NAME
        ).build()

    @Provides
    @Singleton
    fun provideNotificationDao(roomInstance: RoomInstance) : NotificationsDAO = roomInstance.notificationsDAO

    @Provides
    @Singleton
    fun provideMonitoringDAO(roomInstance: RoomInstance): MonitoringDAO = roomInstance.monitoringDAO
}