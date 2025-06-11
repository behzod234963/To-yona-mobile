package com.mr.anonym.data.implementations.remote

import androidx.annotation.Keep
import com.mr.anonym.data.instance.remote.UserApiService
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.repository.remote.UserRepository
import com.mr.anonym.domain.response.DecodeTokenResponse
import com.mr.anonym.domain.response.DeleteUserResponse
import com.mr.anonym.domain.response.LoginRequest
import com.mr.anonym.domain.response.LoginResponse
import com.mr.anonym.domain.response.RefreshTokenRequest
import com.mr.anonym.domain.response.RefreshTokenResponse
import retrofit2.Call
import retrofit2.Response

@Keep class UserRepositoryImpl( private val userApiService: UserApiService): UserRepository {
    override suspend fun loginUser(user: LoginRequest): Response<LoginResponse> = userApiService.loginUser(user)
    override suspend fun registerUser(user: UserModelItem): Response<UserModelItem> = userApiService.registerUser(user)
    override suspend fun getUserByID(userID: Int): Response<UserModelItem> = userApiService.getUserByID(userID)
    override suspend fun updateUser(user: UserModelItem): Response<UserModelItem> = userApiService.updateUser(user)
    override suspend fun searchUser(searchText: String): Response<List<UserModelItem>> = userApiService.searchUser(searchText)
    override fun refreshToken(refreshToken: RefreshTokenRequest): Call<RefreshTokenResponse> = userApiService.refreshToken(refreshToken)
    override suspend fun decodeToken(accessToken: String): Response<DecodeTokenResponse> = userApiService.decodeToken(accessToken)
    override suspend fun deleteUser(): Response<DeleteUserResponse> = userApiService.deleteUser()
}