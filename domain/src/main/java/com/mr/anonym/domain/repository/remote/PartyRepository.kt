package com.mr.anonym.domain.repository.remote

import androidx.paging.PagingData
import com.mr.anonym.domain.model.PartysItem
import kotlinx.coroutines.flow.Flow

interface PartyRepository {

    fun getAllParty(): Flow<PagingData<PartysItem>>
}