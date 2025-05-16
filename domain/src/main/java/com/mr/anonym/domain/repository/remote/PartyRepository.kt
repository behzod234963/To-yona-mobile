package com.mr.anonym.domain.repository.remote

import androidx.paging.PagingData
import com.mr.anonym.domain.model.PartysItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Path

interface PartyRepository {

    suspend fun addEvent(
        userId: Int,
        partyModel: PartysItem
    ): Response<Boolean>
    fun getAllParty(): Flow<PagingData<PartysItem>>
    suspend fun getPartyByID(id: Int): Response<PartysItem>
    suspend fun deleteEvent(id: Int): Response<String>
}