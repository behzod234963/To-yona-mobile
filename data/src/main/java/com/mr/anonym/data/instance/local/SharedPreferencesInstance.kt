package com.mr.anonym.data.instance.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPreferencesInstance(private val context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("SharedPref",
        Context.MODE_PRIVATE)
    fun saveLanguage(language: String){
        sharedPreferences.edit { putString("saveLanguage", language) }
    }
    fun getLanguage(): String?{
        return sharedPreferences.getString("saveLanguage","uz")
    }
    fun saveFirstTimeState(state: Boolean){
        sharedPreferences.edit { putBoolean("firstTimeState", state) }
    }
    fun getFirstTimeState(): Boolean{
        return sharedPreferences.getBoolean("firstTimeState",true)
    }
    fun saveNewPinState(state: Boolean){
        sharedPreferences.edit{ putBoolean("newPinState",state) }
    }
    fun getNewPinState(): Boolean{
        return sharedPreferences.getBoolean("newPinState", false)
    }
    fun saveIsLoggedIn(state: Boolean){
        sharedPreferences.edit{ putBoolean("IsLoggedIn",state) }
    }
    fun getIsLoggedIn(): Boolean{
        return sharedPreferences.getBoolean("IsLoggedIn", false)
    }
}