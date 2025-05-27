package com.mr.anonym.toyonamobile.di.module

import android.content.Context
import androidx.room.Room
import com.mr.anonym.data.auth.AuthInterceptor
import com.mr.anonym.data.auth.TokenAuthenticator
import com.mr.anonym.data.auth.TokenManager
import com.mr.anonym.data.implementations.local.NotificationsRepositoryImpl
import com.mr.anonym.data.implementations.remote.CardRepositoryImpl
import com.mr.anonym.data.implementations.remote.FriendsRepositoryImpl
import com.mr.anonym.data.implementations.remote.PartyRepositoryImpl
import com.mr.anonym.data.implementations.remote.UserRepositoryImpl
import com.mr.anonym.data.instance.local.DataStoreInstance
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.data.instance.local.room.NotificationsDAO
import com.mr.anonym.data.instance.local.room.RoomInstance
import com.mr.anonym.data.instance.remote.CardApiService
import com.mr.anonym.data.instance.remote.FriendsApiService
import com.mr.anonym.data.instance.remote.PartyApiService
import com.mr.anonym.data.instance.remote.UserApiService
import com.mr.anonym.domain.repository.local.NotificationsRepository
import com.mr.anonym.domain.repository.remote.CardRepository
import com.mr.anonym.domain.repository.remote.FriendsRepository
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
import javax.inject.Provider
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

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideNotificationDao(roomInstance: RoomInstance): NotificationsDAO =
        roomInstance.notificationsDAO

    @Provides
    @Singleton
    fun provideNotificationRepository(notificationsDAO: NotificationsDAO): NotificationsRepository =
        NotificationsRepositoryImpl(notificationsDAO)

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
    fun provideAuthInterceptor(sharedPreferences: SharedPreferencesInstance): AuthInterceptor =
        AuthInterceptor(tokenProvider = sharedPreferences)

    @Provides
    @Singleton
    fun provideTokenManager(
        userApiProvider: Provider<UserApiService>,
        sharedPrefs: SharedPreferencesInstance
    ): TokenManager = TokenManager(userApiProvider = userApiProvider, sharedPreferences =  sharedPrefs)

    @Provides
    @Singleton
    fun provideTokenAuthenticator(
        tokenManager: TokenManager,
        sharedPreferences: SharedPreferencesInstance,
    ): TokenAuthenticator =
        TokenAuthenticator(tokenManager, sharedPreferences)

    @Provides
    @Singleton
    fun provideOkHTTP(
        authInterceptor: AuthInterceptor,
        tokenAuthenticator: TokenAuthenticator
    ): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .authenticator(tokenAuthenticator)
            .build()

    @Provides
    @Singleton
    fun provideRetrofit2(
        okHttpClient: OkHttpClient
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApiService =
        retrofit.create(UserApiService::class.java)

    @Provides
    @Singleton
    fun proviePartyApi(retrofit: Retrofit): PartyApiService =
        retrofit.create(PartyApiService::class.java)

    @Provides
    @Singleton
    fun provideFriendsApiService(retrofit: Retrofit): FriendsApiService = retrofit.create(
        FriendsApiService::class.java)

    @Provides
    @Singleton
    fun provieCardApi(retrofit: Retrofit): CardApiService =
        retrofit.create(CardApiService::class.java)

    @Provides
    @Singleton
    fun provideUserRepository(api: UserApiService): UserRepository = UserRepositoryImpl(api)

    @Provides
    @Singleton
    fun providePartyRepository(api: PartyApiService): PartyRepository = PartyRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideCardRepository(api: CardApiService): CardRepository = CardRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideFriendsRepository(api: FriendsApiService): FriendsRepository = FriendsRepositoryImpl(api)
}