package com.mr.anonym.data.instance.local

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import androidx.core.content.edit
import com.mr.anonym.data.R
import com.mr.anonym.data.crypto.AeadManager.getAead

class SharedPreferencesInstance( private val context: Context) {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("SharedPref", Context.MODE_PRIVATE)

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

    fun isDarkTheme(state: Boolean){
        sharedPreferences.edit { putBoolean("isDarkTheme",state) }
    }
    fun getDarkThemeState(): Boolean {
        return sharedPreferences.getBoolean("isDarkTheme",false)
    }

    fun isSystemTheme(state: Boolean){
        sharedPreferences.edit { putBoolean("isSystemThemeApplied",state) }
    }
    fun getSystemThemeState(): Boolean{
        return sharedPreferences.getBoolean("isSystemThemeApplied",true)
    }

    fun isThemeChanged(state: Boolean){
        sharedPreferences.edit { putBoolean("isThemeChanged",state) }
    }
    fun isThemeChangedState(): Boolean{
        return sharedPreferences.getBoolean("isThemeChanged",false)
    }

    fun saveId(id: Int){
        sharedPreferences.edit{ putInt("saveId",id) }
    }
    fun getID(): Int{
        return sharedPreferences.getInt("saveId",-1)
    }

    fun saveToken(token: String){
        val aead = getAead(context)
        val encrypted = aead.encrypt( token.toByteArray(),null )
        val encryptedBase64 = Base64.encodeToString( encrypted, Base64.DEFAULT )
        sharedPreferences.edit { putString( "saveToken",encryptedBase64 ) }
    }
    fun getToken(): String?{
        val encryptedBase64 = sharedPreferences.getString("saveToken",null)?:return null
        val encrypted = Base64.decode( encryptedBase64, Base64.DEFAULT )
        val aead = getAead(context)
        val decrypted = aead.decrypt( encrypted,null )
        return String(decrypted)
    }

    fun savePinCode(pinCode: String){
        val aead = getAead(context)
        val encrypted = aead.encrypt( pinCode.toByteArray(),null )
        val encryptedBase64 = Base64.encodeToString( encrypted, Base64.DEFAULT )
        sharedPreferences.edit { putString( "pinCode",encryptedBase64 ) }
    }
    fun getPinCode(): String?{
        val encryptedBase64 = sharedPreferences.getString("pinCode",null)?:return null
        val encrypted = Base64.decode( encryptedBase64, Base64.DEFAULT )
        val aead = getAead(context)
        val decrypted = aead.decrypt( encrypted,null )
        return String(decrypted)
    }

    fun savePhoneNumber(phoneNumber: String){
        val aead = getAead(context)
        val encrypted = aead.encrypt( phoneNumber.toByteArray(),null )
        val encryptedBase64 = Base64.encodeToString( encrypted, Base64.DEFAULT )
        sharedPreferences.edit { putString( "phoneNumber",encryptedBase64 ) }
    }
    fun getPhoneNumber(): String?{
        val encryptedBase64 = sharedPreferences.getString("phoneNumber",null)?:return null
        val encrypted = Base64.decode( encryptedBase64, Base64.DEFAULT )
        val aead = getAead(context)
        val decrypted = aead.decrypt( encrypted,null )
        return String(decrypted)
    }

    fun saveCardNumber(cardNumber: String){
        val aead = getAead(context)
        val encrypted = aead.encrypt( cardNumber.toByteArray(),null )
        val encryptedBase64 = Base64.encodeToString( encrypted, Base64.DEFAULT )
        sharedPreferences.edit { putString( "cardNumber",encryptedBase64 ) }
    }
    fun getCardNumber(): String?{
        val encryptedBase64 = sharedPreferences.getString("cardNumber",null)?:return null
        val encrypted = Base64.decode( encryptedBase64, Base64.DEFAULT )
        val aead = getAead(context)
        val decrypted = aead.decrypt( encrypted,null )
        return String(decrypted)
    }
}