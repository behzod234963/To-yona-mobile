package com.mr.anonym.domain.repository.remote

import androidx.annotation.Keep
import androidx.paging.PagingData
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.response.AddPartyResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

@Keep interface PartyRepository {

    suspend fun addParty(partyModel: PartysItem): Response<AddPartyResponse>
    suspend fun updateParty(partyID: Int,partyModel: PartysItem): Response<Boolean>
    fun getAllParty(): Flow<PagingData<PartysItem>>
    suspend fun getPartyByID(id: Int): Response<PartysItem>
    suspend fun deleteParty(id: Int): Response<String>
    suspend fun getUserParties(userID: Int): Response<UserModelItem>
}