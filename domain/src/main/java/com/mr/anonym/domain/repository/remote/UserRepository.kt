package com.mr.anonym.domain.repository.remote

import androidx.annotation.Keep
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.response.DecodeTokenResponse
import com.mr.anonym.domain.response.DeleteUserResponse
import com.mr.anonym.domain.response.LoginRequest
import com.mr.anonym.domain.response.LoginResponse
import com.mr.anonym.domain.response.RefreshTokenRequest
import com.mr.anonym.domain.response.RefreshTokenResponse
import retrofit2.Call
import retrofit2.Response

@Keep interface UserRepository {

    suspend fun loginUser(user: LoginRequest): Response<LoginResponse>
    suspend fun registerUser(user: UserModelItem): Response<UserModelItem>
    suspend fun getUserByID(userID: Int): Response<UserModelItem>
    suspend fun updateUser(user: UserModelItem): Response<UserModelItem>
    suspend fun searchUser(searchText: String): Response<List<UserModelItem>>
    fun refreshToken(refreshToken: RefreshTokenRequest): Call<RefreshTokenResponse>
    suspend fun decodeToken(accessToken: String): Response<DecodeTokenResponse>
    suspend fun deleteUser(): Response<DeleteUserResponse>
}