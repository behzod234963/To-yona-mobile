package com.mr.anonym.toyonamobile.presentation.utils

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import java.util.Locale

class LocaleConfigurations(private val context: Context) {

    val sharedPreferences = SharedPreferencesInstance(context)

    fun Activity.setLocale(language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val config = Configuration(resources.configuration)
        config.setLocale(locale)
        resources.updateConfiguration(config, resources.displayMetrics)
        recreate()
    }

    fun Activity.applySavedLanguage() {
        val language = sharedPreferences.getLanguage() ?: "uz"
        val locale = Locale(language)
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