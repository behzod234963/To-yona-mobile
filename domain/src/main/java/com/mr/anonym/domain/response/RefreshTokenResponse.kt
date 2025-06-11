package com.mr.anonym.domain.response

import androidx.annotation.Keep

@Keep data class RefreshTokenRequest(
    val refreshToken: String
)

@Keep data class RefreshTokenResponse (
    val newAccessToken: String = ""
)