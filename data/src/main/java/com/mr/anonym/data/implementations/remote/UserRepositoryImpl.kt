package com.mr.anonym.data.implementations.remote

import com.mr.anonym.data.instance.remote.UserApiService
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.repository.remote.UserRepository
import com.mr.anonym.domain.response.LoginRequest
import com.mr.anonym.domain.response.LoginResponse
import retrofit2.Response

class UserRepositoryImpl( private val userApiService: UserApiService): UserRepository {
    override suspend fun loginUser(user: LoginRequest): Response<LoginResponse> = userApiService.loginUser(user)
    override suspend fun registerUser(user: UserModelItem): Response<UserModelItem> = userApiService.registerUser(user)
    override suspend fun getUserByID(id: Int): Response<UserModelItem> = userApiService.getUserByID(id)
    override suspend fun updateUser(id: Int, user: UserModelItem): Response<UserModelItem> = userApiService.updateUser(id,user)
    override suspend fun searchUser(searchText: String): Response<List<UserModelItem>> = userApiService.searchUser(searchText)
}