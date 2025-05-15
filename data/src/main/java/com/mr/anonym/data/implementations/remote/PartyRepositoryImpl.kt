package com.mr.anonym.data.implementations.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mr.anonym.data.instance.remote.PartyApiService
import com.mr.anonym.data.paging.PartyPagingSource
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.repository.remote.PartyRepository
import kotlinx.coroutines.flow.Flow

class PartyRepositoryImpl(private val partyApiService: PartyApiService): PartyRepository {
    override fun getAllParty(): Flow<PagingData<PartysItem>> {
        return Pager(
            config = PagingConfig(20),
            pagingSourceFactory = { PartyPagingSource(api = partyApiService) }
        ).flow
    }
}