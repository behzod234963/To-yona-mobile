package com.mr.anonym.data.instance.remote

import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.response.LoginRequest
import com.mr.anonym.domain.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiService {

    @POST("/login")
    suspend fun loginUser(@Body user: LoginRequest): Response<LoginResponse>

    @POST("/register")
    suspend fun registerUser(@Body user: UserModelItem): Response<UserModelItem>

    @PUT("/update/{id}")
    suspend fun updateUser(
        @Path("id") id: Int,
        @Body user: UserModelItem
    ): Response<UserModelItem>

    @GET("/getbyid/{id}")
    suspend fun getUserByID(
        @Path("id") id: Int
    ): Response<UserModelItem>

    @GET("/getallusersearch/")
    suspend fun searchUser(
        @Query("search") searchText: String
    ): Response<List<UserModelItem>>
}