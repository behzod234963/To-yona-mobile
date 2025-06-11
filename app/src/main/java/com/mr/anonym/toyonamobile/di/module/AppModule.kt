package com.mr.anonym.toyonamobile.di.module

import android.content.Context
import com.mr.anonym.toyonamobile.presentation.managers.LocaleConfigurations
import com.mr.anonym.toyonamobile.presentation.managers.PermissionController
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