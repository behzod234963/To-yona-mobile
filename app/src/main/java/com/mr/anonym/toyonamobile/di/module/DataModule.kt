package com.mr.anonym.toyonamobile.di.module

import android.content.Context
import androidx.room.Room
import com.mr.anonym.data.implementations.local.CardRepositoryImpl
import com.mr.anonym.data.implementations.local.NotificationsRepositoryImpl
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.data.instance.local.room.CardDAO
import com.mr.anonym.data.instance.local.room.NotificationsDAO
import com.mr.anonym.data.instance.local.room.RoomInstance
import com.mr.anonym.domain.repository.local.CardRepository
import com.mr.anonym.domain.repository.local.NotificationsRepository
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
    fun provideRoomInstance(@ApplicationContext context: Context): RoomInstance {
        return Room.databaseBuilder(
            context,
            RoomInstance::class.java,
            RoomInstance.DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNotificationDao(roomInstance: RoomInstance): NotificationsDAO =
        roomInstance.notificationsDAO

    @Provides
    @Singleton
    fun provideCardDAO(roomInstance: RoomInstance): CardDAO = roomInstance.cardDAO

    @Provides
    @Singleton
    fun provideNotificationRepository(notificationsDAO: NotificationsDAO): NotificationsRepository =
        NotificationsRepositoryImpl(notificationsDAO)

    @Provides
    @Singleton
    fun provideCardRepository(cardDAO: CardDAO): CardRepository =
        CardRepositoryImpl(cardDAO)

    @Provides
    @Singleton
    fun provideDataStoreInstance(@ApplicationContext context: Context): DataStoreInstance =
        DataStoreInstance(context)

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferencesInstance =
        SharedPreferencesInstance(context)
}