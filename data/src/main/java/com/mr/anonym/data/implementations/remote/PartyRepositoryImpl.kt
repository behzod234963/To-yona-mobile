package com.mr.anonym.data.implementations.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mr.anonym.data.instance.remote.PartyApiService
import com.mr.anonym.data.paging.PartyPagingSource
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.repository.remote.PartyRepository
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class PartyRepositoryImpl(private val partyApiService: PartyApiService): PartyRepository {

    override suspend fun addParty(userId: Int, partyModel: PartysItem): Response<Boolean> = partyApiService.addParty(userId,partyModel)
    override suspend fun updateParty(partyID: Int, partyModel: PartysItem): Response<Boolean> = partyApiService.updateParty(partyID,partyModel)
    override fun getAllParty(): Flow<PagingData<PartysItem>> {
        return Pager(
            config = PagingConfig(20),
            pagingSourceFactory = { PartyPagingSource(api = partyApiService) }
        ).flow
    }
    override suspend fun getPartyByID(id: Int): Response<PartysItem> = partyApiService.getPartyByID(id)
    override suspend fun deleteParty(id: Int): Response<String> = partyApiService.deleteParty(id)
    override suspend fun getUserParties(id: Int): Response<UserModelItem> = partyApiService.getUserParties(id)
}