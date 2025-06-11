package com.mr.anonym.toyonamobile.presentation.managers

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import java.util.Locale

class LocaleConfigurations(private val context: Context) {

    val sharedPreferences = SharedPreferencesInstance(context)

    fun Activity.setLocale(language: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val locale = Locale.Builder()
                .setLanguage(language)
                .build()
            Locale.setDefault(locale)
            val config = Configuration(resources.configuration)
            config.setLocale(locale)
            applicationContext.createConfigurationContext(config)
            restartApp(context)
        }else{
            val locale = Locale.Builder()
                .setLanguage(language)
                .build()
            Locale.setDefault(locale)
            val config = Configuration(resources.configuration)
            config.setLocale(locale)
            applicationContext.createConfigurationContext(config)
            restartApp(context)
        }
    }

    fun setApplicationLocales(activity: Activity, language: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(language))
            sharedPreferences.saveLanguage(language)
            Log.d("UtilsLogging", "setApplicationLocales: ${getPrimaryLocale()}")
        } else {
            sharedPreferences.saveLanguage(language)
            activity.setLocale(language)
            Log.d("UtilsLogging", "setApplicationLocales: ${getPrimaryLocale()}")
        }
    }

    fun Activity.applySavedLanguage() {
        val language = sharedPreferences.getLanguage() ?: "uz"
        val locale = Locale.Builder()
            .setLanguage(language)
            .build()
        Locale.setDefault(locale)
        val config = Configuration(resources.configuration)
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)

    }

    fun getPrimaryLocale(): String {
        val primaryLocale = context.resources.configuration.locales[0]
        return primaryLocale.displayName
    }
}