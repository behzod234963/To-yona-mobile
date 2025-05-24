package com.mr.anonym.domain.response

data class LoginResponse (
    val accessToken: String,
    val refreshToken: String
)
data class LoginRequest(
    val phonenumber: String,
    val password: String
)