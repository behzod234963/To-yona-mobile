package com.mr.anonym.data.auth

import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(
    private val tokenManager: TokenManager,
    private val sharedPreferences: SharedPreferencesInstance,
) : Authenticator{
    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(this){
            val newToken = tokenManager.refreshToken()
            return if ( newToken != null ){
                sharedPreferences.saveAccessToken(newToken)
                response.request.newBuilder()
                    .header("Authorization","Bearer $newToken")
                    .build()
            }else null
        }
    }
}