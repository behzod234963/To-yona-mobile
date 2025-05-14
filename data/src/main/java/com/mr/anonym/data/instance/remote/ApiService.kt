package com.mr.anonym.data.instance.remote

import com.mr.anonym.domain.model.UserModel
import com.mr.anonym.domain.response.LoginRequest
import com.mr.anonym.domain.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/login")
    suspend fun loginUser(@Body user: LoginRequest): Response<LoginResponse>

    @POST("/register")
    suspend fun registerUser(@Body user: UserModel): Response<UserModel>
}