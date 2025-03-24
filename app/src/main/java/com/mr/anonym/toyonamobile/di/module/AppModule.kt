package com.mr.anonym.toyonamobile.di.module

import android.content.Context
import com.mr.anonym.toyonamobile.presentation.utils.LocaleConfigurations
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
}