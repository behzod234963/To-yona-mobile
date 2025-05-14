package com.mr.anonym.data.implementations.remote

import com.mr.anonym.data.instance.remote.ApiService
import com.mr.anonym.domain.model.UserModel
import com.mr.anonym.domain.repository.remote.UserRepository
import com.mr.anonym.domain.response.LoginRequest
import com.mr.anonym.domain.response.LoginResponse
import retrofit2.Response

class UserRepositoryImpl( private val apiService: ApiService): UserRepository {
    override suspend fun loginUser(user: LoginRequest): Response<LoginResponse> = apiService.loginUser(user)
    override suspend fun registerUser(user: UserModel): Response<UserModel> = apiService.registerUser(user)
}