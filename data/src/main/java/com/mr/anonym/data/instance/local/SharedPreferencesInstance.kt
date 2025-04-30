package com.mr.anonym.data.instance.local

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.mr.anonym.data.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SharedPreferencesInstance(private val context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("SharedPref",
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
    fun changePinProcess(state: Boolean){
        sharedPreferences.edit { putBoolean("changePinProcess",state) }
    }
    fun changePinProcessState(): Boolean{
        return sharedPreferences.getBoolean("changePinProcess",false)
    }

    fun saveAvatar(avatar: Int){
        sharedPreferences.edit { putInt("avatar", avatar) }
    }
    fun getAvatar(): Int{
        return sharedPreferences.getInt("avatar",R.drawable.ic_default_avatar)
    }
    fun saveFirstname(firstname: String){
        sharedPreferences.edit { putString("firstname",firstname) }
    }
    fun getFirstname(): String {
        return sharedPreferences.getString("firstname","")?:""
    }

    fun saveLastname(lastname: String){
        sharedPreferences.edit{ putString("lastname",lastname) }
    }
    fun getLastname(): String{
        return sharedPreferences.getString("lastname","")?:""
    }

    fun saveBiometricAuthState(state: Boolean){
        sharedPreferences.edit { putBoolean("bioState",state) }
    }
    fun getBiometricAuthState(): Boolean{
        return sharedPreferences.getBoolean("bioState",false)
    }
    fun saveIsBiometricAuthOn(state: Boolean){
        sharedPreferences.edit { putBoolean("BiometricAuthOff",state) }
    }
    fun getIsBiometricAuthOn(): Boolean{
        return sharedPreferences.getBoolean("BiometricAuthOff",false)
    }
}