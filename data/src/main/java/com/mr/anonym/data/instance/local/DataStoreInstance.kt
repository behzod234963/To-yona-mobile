package com.mr.anonym.data.instance.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.mr.anonym.data.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("dataStore")
class DataStoreInstance(private val context: Context) {

    suspend fun saveBiometricAuthState(state: Boolean){
        val key = booleanPreferencesKey("bioState")
        context.dataStore.edit {
            it[key] = state
        }
    }
    fun getBiometricAuthState(): Flow<Boolean>{
        val key = booleanPreferencesKey("bioState")
        return context.dataStore.data.map {
            it[key]?:false
        }
    }
    suspend fun savePinCode(pinCode: String){
        val key = stringPreferencesKey("pinCode")
        context.dataStore.edit {
            it[key] = pinCode
        }
    }
    fun getPinCode(): Flow<String>{
        val key = stringPreferencesKey("pinCode")
        return context.dataStore.data.map {
            it[key]?:""
        }
    }
    suspend fun saveIsBiometricAuthOn(state: Boolean){
        val key = booleanPreferencesKey("BiometricAuthOff")
        context.dataStore.edit {
            it[key] = state
        }
    }
    fun getIsBiometricAuthOn(): Flow<Boolean>{
        val key = booleanPreferencesKey("BiometricAuthOff")
        return context.dataStore.data.map {
            it[key]?:false
        }
    }
    suspend fun saveIsStateOnPause(state: Boolean){
        val key = booleanPreferencesKey("IsStateOnPause")
        context.dataStore.edit {
            it[key] = state
        }
    }
    fun getIsStateOnPause(): Flow<Boolean>{
        val key = booleanPreferencesKey("IsStateOnPause")
        return context.dataStore.data.map {
            it[key]?:false
        }
    }
    suspend fun saveIsEnterCompleted(state: Boolean){
        val key = booleanPreferencesKey("IsEnterCompleted")
        context.dataStore.edit {
            it[key] = state
        }
    }
    fun getIsEnterCompleted(): Flow<Boolean>{
        val key = booleanPreferencesKey("IsEnterCompleted")
        return context.dataStore.data.map {
            it[key]?:false
        }
    }
    suspend fun saveAvatar(avatar: Int){
        val key = intPreferencesKey("profileavatar")
        context.dataStore.edit {
            it[key] = avatar
        }
    }
    fun getAvatar(): Flow<Int>{
        val key = intPreferencesKey("profileavatar")
        return context.dataStore.data.map {
            it[key]?: R.drawable.ic_default_avatar
        }
    }
}