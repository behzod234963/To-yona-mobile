package com.mr.anonym.toyonamobile.di.module

import android.content.Context
import com.mr.anonym.data.instance.remote.ApiService
import com.mr.anonym.toyonamobile.presentation.constants.BASE_URL
import com.mr.anonym.toyonamobile.presentation.utils.LocaleConfigurations
import com.mr.anonym.toyonamobile.presentation.utils.PermissionController
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
class AppModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context) = context

    @Provides
    @Singleton
    fun provideLocaleConfigurations(@ApplicationContext context: Context): LocaleConfigurations =
        LocaleConfigurations(context)

    @Provides
    @Singleton
    fun providePermissionController(@ApplicationContext context: Context): PermissionController =
        PermissionController(context)
}