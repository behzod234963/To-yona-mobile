package com.mr.anonym.data.instance.remote

import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.model.UserModelItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CardApiService {

    @POST("/card/add")
    suspend fun addCard(
        @Body cardModel: CardModel
    ): Response<CardModel>

    @GET("/card/getall")
    suspend fun getAllCard(): Response<List<CardModel>>

    @GET("/card/getby/{id}")
    suspend fun getCardByID(
        @Path("id") cardID: Int
    ): Response<CardModel>

    @PUT("/card/update/{id}")
    suspend fun updateCard(
        @Path("id") cardID: Int,
        @Body cardModel: CardModel
    ): Response<CardModel>

    @DELETE("/card/delete/{id}")
    suspend fun deleteCard(
        @Path("id") cardID: Int,
    ): Response<String>

    @GET("/getbyid/{id}")
    suspend fun getUserCards(
        @Path("id") userID: Int
    ): Response<UserModelItem>
}