package com.mr.anonym.domain.repository.remote

import androidx.paging.PagingData
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.model.UserModelItem
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface PartyRepository {

    suspend fun addParty(userId: Int, partyModel: PartysItem): Response<Boolean>
    suspend fun updateParty(partyID: Int,partyModel: PartysItem): Response<Boolean>
    fun getAllParty(): Flow<PagingData<PartysItem>>
    suspend fun getPartyByID(id: Int): Response<PartysItem>
    suspend fun deleteParty(id: Int): Response<String>
    suspend fun getUserParties(userID: Int): Response<UserModelItem>
}