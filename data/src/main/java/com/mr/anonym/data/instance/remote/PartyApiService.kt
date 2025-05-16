package com.mr.anonym.data.instance.remote

import com.mr.anonym.domain.model.PartyModel
import com.mr.anonym.domain.model.PartysItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface PartyApiService {

    @POST("/party/add/{userId}")
    suspend fun addEvent(
        @Path("userId") userId: Int,
        @Body partyModel: PartysItem
    ): Response<Boolean>

    @DELETE("/party/delete/{id}")
    suspend fun deleteEvent(
        @Path("id") id: Int
    ): Response<String>

    @GET("/party/getallpag/")
    suspend fun getAllParty(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<PartyModel>

    @GET("/party/getby/{id}")
    suspend fun getPartyByID(
        @Path("id") id: Int
    ): Response<PartysItem>
}