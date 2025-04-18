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
    suspend fun saveFirstname(firstname: String){
        val key = stringPreferencesKey("firstname")
        context.dataStore.edit {
            it[key] = firstname
        }
    }
    fun getFirstname(): Flow<String>{
        val key = stringPreferencesKey("firstname")
        return context.dataStore.data.map {
            it[key]?:""
        }
    }
    suspend fun saveLastname(lastname: String){
        val key = stringPreferencesKey("lastname")
        context.dataStore.edit {
            it[key] = lastname
        }
    }
    fun getLastname(): Flow<String>{
        val key = stringPreferencesKey("lastname")
        return context.dataStore.data.map {
            it[key]?:""
        }
    }
    suspend fun savePhoneNumber(phoneNumber: String){
        val key = stringPreferencesKey("phoneNumber")
        context.dataStore.edit {
            it[key] = phoneNumber
        }
    }
    fun getPhoneNumber(): Flow<String>{
        val key = stringPreferencesKey("phoneNumber")
        return context.dataStore.data.map {
            it[key]?:""
        }
    }
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
    suspend fun saveCardNumber(cardNumber: String){
        val key = stringPreferencesKey("cardNumber")
        context.dataStore.edit {
            it[key] = cardNumber
        }
    }
    fun getCardNumber(): Flow<String>{
        val key = stringPreferencesKey("cardNumber")
        return context.dataStore.data.map {
            it[key]?:""
        }
    }
    suspend fun saveCardHolder(cardHolder: String){
        val key = stringPreferencesKey("cardHolder")
        context.dataStore.edit {
            it[key] = cardHolder
        }
    }
    fun getCardHolder(): Flow<String>{
        val key = stringPreferencesKey("cardHolder")
        return context.dataStore.data.map {
            it[key]?:""
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
    suspend fun isDarkTheme(state: Boolean){
        val key = booleanPreferencesKey("isDarkTheme")
        context.dataStore.edit {
            it[key] = state
        }
    }
    fun getDarkThemeState(): Flow<Boolean>{
        val key = booleanPreferencesKey("isDarkTheme")
        return context.dataStore.data.map{
            it[key]?:false
        }
    }
    suspend fun isSystemTheme(state: Boolean){
        val key = booleanPreferencesKey("isSystemThemeApplied")
        context.dataStore.edit {
            it[key] = state
        }
    }
    fun getSystemThemeState(): Flow<Boolean>{
        val key = booleanPreferencesKey("isSystemThemeApplied")
        return context.dataStore.data.map{
            it[key]?:true
        }
    }
}