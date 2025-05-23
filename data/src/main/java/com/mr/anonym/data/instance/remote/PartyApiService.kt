package com.mr.anonym.data.instance.remote

import com.mr.anonym.domain.model.PartyModel
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.model.UserModelItem
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PartyApiService {

    @POST("/party/add/{id}")
    suspend fun addParty(
        @Path("id") userId: Int,
        @Body partyModel: PartysItem
    ): Response<Boolean>

    @PUT("/party/update/{id}")
    suspend fun updateParty(
        @Path("id") partyID: Int,
        @Body partyModel: PartysItem
    ): Response<Boolean>

    @DELETE("/party/delete/{id}")
    suspend fun deleteParty(
        @Path("id") partyID: Int
    ): Response<String>

    @GET("/party/getallpag/")
    suspend fun getAllParty(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<PartyModel>

    @GET("/party/getby/{id}")
    suspend fun getPartyByID(
        @Path("id") partyID: Int
    ): Response<PartysItem>

    @GET("/getbyid/{userID}")
    suspend fun getUserParties(
        @Path("userID") userID: Int
    ): Response<UserModelItem>
}