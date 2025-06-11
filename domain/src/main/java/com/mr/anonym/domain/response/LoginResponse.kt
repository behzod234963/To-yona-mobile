package com.mr.anonym.domain.response

import androidx.annotation.Keep

@Keep data class LoginResponse (
    val accessToken: String,
    val refreshToken: String
)
@Keep data class LoginRequest(
    val phonenumber: String,
    val password: String
)