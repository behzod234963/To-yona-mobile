package com.mr.anonym.data.auth

import androidx.annotation.Keep
import com.mr.anonym.data.instance.local.SharedPreferencesInstance
import okhttp3.Interceptor
import okhttp3.Response

@Keep class AuthInterceptor(private val tokenProvider: SharedPreferencesInstance,): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization","Bearer ${tokenProvider.getAccessToken()}")
            .build()
        return chain.proceed(request)
    }
}