package com.mr.anonym.domain.repository.remote

import com.mr.anonym.domain.model.UserModel
import com.mr.anonym.domain.response.LoginRequest
import com.mr.anonym.domain.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserRepository {

    suspend fun loginUser(user: LoginRequest): Response<LoginResponse>
    suspend fun registerUser(user: UserModel): Response<UserModel>
}