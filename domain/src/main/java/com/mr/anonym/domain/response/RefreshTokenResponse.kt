package com.mr.anonym.domain.response

data class RefreshTokenRequest(
    val refreshToken: String
)

data class RefreshTokenResponse (
    val newAccessToken: String = ""
)