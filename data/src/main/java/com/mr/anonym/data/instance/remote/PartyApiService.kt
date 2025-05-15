package com.mr.anonym.data.instance.remote

import com.mr.anonym.domain.model.PartyModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PartyApiService {

    @GET("/party/getallpag/")
    suspend fun getAllParty(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<PartyModel>
}