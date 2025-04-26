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
    fun saveIsProfileSettingsState(state: Boolean){
        sharedPreferences.edit{ putBoolean("IsProfileSettingsState",state) }
    }
    fun getIsProfileSettingsState(): Boolean{
        return sharedPreferences.getBoolean("IsProfileSettingsState", false)
    }
    fun addCardProcess(state: Boolean){
        sharedPreferences.edit { putBoolean("addCardProcess",state) }
    }
    fun addCardProcessState(): Boolean{
       return sharedPreferences.getBoolean("addCardProcess",false)
    }
    fun editProfileProcess(state: Boolean){
        sharedPreferences.edit { putBoolean("editProfileProcess",state) }
    }
    fun editProfileProcessState(): Boolean{
        return sharedPreferences.getBoolean("editProfileProcess",false)
    }
    fun openSecurityContent(state: Boolean){
        sharedPreferences.edit { putBoolean("openSecurityContent",state) }
    }
    fun openSecurityContentState(): Boolean{
        return sharedPreferences.getBoolean("openSecurityContent",false)
    }
    fun changePinProcess(state: Boolean){
        sharedPreferences.edit { putBoolean("changePinProcess",state) }
    }
    fun changePinProcessState(): Boolean{
        return sharedPreferences.getBoolean("changePinProcess",false)
    }
    fun changePhoneNumberProcess(state: Boolean){
        sharedPreferences.edit{ putBoolean("changePhoneNumberProcess",state) }
    }
    fun isChangePhoneNumberProcessState(): Boolean{
        return sharedPreferences.getBoolean("changePhoneNumberProcess",false)
    }
}