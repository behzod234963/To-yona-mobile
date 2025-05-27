package com.mr.anonym.data.instance.remote

import com.mr.anonym.domain.model.PartyModel
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.response.AddPartyResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PartyApiService {

    @POST("/party/add")
    suspend fun addParty(
        @Body partyModel: PartysItem
    ): Response<AddPartyResponse>

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

    @GET("/getbyid/{id}")
    suspend fun getUserParties(
        @Path("id") userID: Int,
    ): Response<UserModelItem>
}