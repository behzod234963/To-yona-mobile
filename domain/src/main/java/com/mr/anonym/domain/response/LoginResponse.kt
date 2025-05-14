package com.mr.anonym.domain.response

data class LoginResponse (
    val id:Int,
    val message: String
)
data class LoginRequest(
    val phonenumber: String,
    val password: String
)