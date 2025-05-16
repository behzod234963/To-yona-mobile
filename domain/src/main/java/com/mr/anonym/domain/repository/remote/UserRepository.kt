package com.mr.anonym.domain.repository.remote

import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.response.LoginRequest
import com.mr.anonym.domain.response.LoginResponse
import retrofit2.Response

interface UserRepository {

    suspend fun loginUser(user: LoginRequest): Response<LoginResponse>
    suspend fun registerUser(user: UserModelItem): Response<UserModelItem>
    suspend fun getUserByID(id: Int): Response<UserModelItem>
    suspend fun updateUser(id: Int, user: UserModelItem): Response<UserModelItem>
    suspend fun searchUser(searchText: String): Response<List<UserModelItem>>
}