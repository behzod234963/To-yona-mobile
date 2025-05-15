package com.mr.anonym.toyonamobile.di.module

import android.content.Context
import androidx.room.Room
import com.mr.anonym.data.implementations.local.CardRepositoryImpl
import com.mr.anonym.data.implementations.local.MyEventsRepositoryImpl
import com.mr.anonym.data.implementations.local.NotificationsRepositoryImpl
import com.mr.anonym.data.implementations.remote.PartyRepositoryImpl
import com.mr.anonym.data.implementations.remote.UserRepositoryImpl
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.data.instance.local.room.CardDAO
import com.mr.anonym.data.instance.local.room.MyEventsDAO
import com.mr.anonym.data.instance.local.room.NotificationsDAO
import com.mr.anonym.data.instance.local.room.RoomInstance
import com.mr.anonym.data.instance.remote.PartyApiService
import com.mr.anonym.data.instance.remote.UserApiService
import com.mr.anonym.domain.repository.local.CardRepository
import com.mr.anonym.domain.repository.local.MyEventsRepository
import com.mr.anonym.domain.repository.local.NotificationsRepository
import com.mr.anonym.domain.repository.remote.PartyRepository
import com.mr.anonym.domain.repository.remote.UserRepository
import com.mr.anonym.toyonamobile.presentation.constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun provideMyEventsDAO(roomInstance: RoomInstance): MyEventsDAO = roomInstance.myEventsDAO

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
    fun provideMyEventsRepository(myEventsDAO: MyEventsDAO): MyEventsRepository =
        MyEventsRepositoryImpl(myEventsDAO)

    @Provides
    @Singleton
    fun provideDataStoreInstance(@ApplicationContext context: Context): DataStoreInstance =
        DataStoreInstance(context)

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferencesInstance =
        SharedPreferencesInstance(context)

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideRetrofit2(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(provideOkHTTP())
            .build()

    @Provides
    @Singleton
    fun provideOkHTTP(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provieUserApi(retrofit: Retrofit): UserApiService = retrofit.create(UserApiService::class.java)

    @Provides
    @Singleton
    fun proviePartyApi(retrofit: Retrofit): PartyApiService = retrofit.create(PartyApiService::class.java)

    @Provides
    @Singleton
    fun provideUserRepository(api: UserApiService): UserRepository = UserRepositoryImpl(api)

    @Provides
    @Singleton
    fun providePartyRepository(api: PartyApiService): PartyRepository = PartyRepositoryImpl(api)
}