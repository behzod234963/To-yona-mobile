package com.mr.anonym.data.instance.local

import android.content.Context
import androidx.annotation.Keep
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore("dataStore")
@Keep class DataStoreInstance(private val context: Context) {

    suspend fun isPasswordForgotten(status: Boolean){
        val key = booleanPreferencesKey("ForgotPassword")
        context.dataStore.edit {
            it[key] = status
        }
    }
    fun isPasswordForgottenState(): Flow<Boolean>{
        val key = booleanPreferencesKey("ForgotPassword")
        return context.dataStore.data.map {
            it[key]?:false
        }
    }
    suspend fun isOldUser(state: Boolean){
        val key = booleanPreferencesKey( "isOldUser" )
        context.dataStore.edit {
            it[key] = state
        }
    }
    fun isOldUserState(): Flow<Boolean>{
        val key = booleanPreferencesKey( "isOldUser" )
        return context.dataStore.data.map {
            it[key]?:false
        }
    }
    suspend fun isPinForgotten(state: Boolean){
        val key = booleanPreferencesKey("ForgotPinCode")
        context.dataStore.edit {
            it[key] = state
        }
    }
    fun isPinForgottenState(): Flow<Boolean>{
        val key = booleanPreferencesKey("ForgotPinCode")
        return context.dataStore.data.map {
            it[key]?:false
        }
    }
    suspend fun saveExpiryDate(date: String){
        val key = stringPreferencesKey("ExpiryDate")
        context.dataStore.edit {
            it[key] = date
        }
    }
    fun getExpiryDate(): Flow<String>{
        val key = stringPreferencesKey("ExpiryDate")
        return context.dataStore.data.map {
            it[key]?:""
        }
    }
    suspend fun saveCardID(id: Int){
        val key = intPreferencesKey("cardIDs")
        context.dataStore.edit {
            it[key] = id
        }
    }
    fun getCardID(): Flow<Int>{
        val key = intPreferencesKey("cardIDs")
        return context.dataStore.data.map {
            it[key]?:-1
        }
    }
    suspend fun showBiometricAuthManually(state: Boolean){
        val key = booleanPreferencesKey("showBiometricAuthManually")
        context.dataStore.edit {
            it[key] = state
        }
    }
    fun showBiometricAuthManuallyState(): Flow<Boolean>{
        val key = booleanPreferencesKey("showBiometricAuthManually")
        return context.dataStore.data.map {
            it[key]?:false
        }
    }
    suspend fun openSecurityContent(state: Boolean){
        val key = booleanPreferencesKey("openSecurityContent")
        context.dataStore.edit {
            it[key] = state
        }
    }
    fun openSecurityContentState(): Flow<Boolean>{
        val key = booleanPreferencesKey("openSecurityContent")
        return context.dataStore.data.map {
            it[key]?:false
        }
    }

    suspend fun isOnline(status: Boolean){
        val key = booleanPreferencesKey("isOnline")
        context.dataStore.edit {
            it[key] = status
        }
    }
    fun isOnlineState(): Flow<Boolean>{
        val key = booleanPreferencesKey("isOnline")
        return context.dataStore.data.map {
            it[key]?:false
        }
    }
}