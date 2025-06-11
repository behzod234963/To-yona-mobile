package com.mr.anonym.data.instance.remote

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
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

@Keep interface UserApiService {

    @POST("/login")
    suspend fun loginUser(@Body user: LoginRequest): Response<LoginResponse>

    @POST("/register")
    suspend fun registerUser(@Body user: UserModelItem): Response<UserModelItem>

    @PUT("/update")
    suspend fun updateUser(
        @Body user: UserModelItem
    ): Response<UserModelItem>

    @GET("/getbyid/{id}")
    suspend fun getUserByID(
        @Path("id") userID: Int
    ): Response<UserModelItem>

    @GET("/getallusersearch/")
    suspend fun searchUser(
        @Query("search") searchText: String
    ): Response<List<UserModelItem>>

    @POST("/refreshtoken")
    fun refreshToken(
        @Body refreshToken: RefreshTokenRequest
    ): Call<RefreshTokenResponse>

    @GET("/decodetoken")
    suspend fun decodeToken(
        @Header("Authorization") accessToken: String
    ): Response<DecodeTokenResponse>

    @DELETE("/delete")
    suspend fun deleteUser(): Response<DeleteUserResponse>
}