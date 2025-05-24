package com.mr.anonym.data.auth

import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import com.mr.anonym.data.instance.remote.UserApiService
import com.mr.anonym.domain.response.RefreshTokenRequest
import javax.inject.Provider

class TokenManager(
    private val sharedPreferences: SharedPreferencesInstance,
    private val userApiProvider: Provider<UserApiService>
) {

    fun refreshToken(): String?{
        val userAPI = userApiProvider.get()
        val refreshToken = RefreshTokenRequest(sharedPreferences.getRefreshToken() ?:return null)
        val response = userAPI.refreshToken(refreshToken).execute()
        return if ( response.isSuccessful ){
            val newAccessToken = response.body()?.newAccessToken
            if (newAccessToken != null){
                sharedPreferences.saveAccessToken(newAccessToken)
                newAccessToken
            }else null
        }else{
            null
        }
    }
}