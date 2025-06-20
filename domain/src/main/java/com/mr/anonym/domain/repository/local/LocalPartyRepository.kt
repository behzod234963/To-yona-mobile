package com.mr.anonym.domain.repository.local

import androidx.annotation.Keep
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mr.anonym.domain.model.LocalPartyModel
import kotlinx.coroutines.flow.Flow

@Keep interface LocalPartyRepository {

    suspend fun insertLocalParty(localParty: LocalPartyModel)
    suspend fun insertAllParty(partyList: List<LocalPartyModel>)
    fun getAllLocalParty(): Flow<List<LocalPartyModel>>
    suspend fun clearAllLocalParty(partyList: List<LocalPartyModel>)
    suspend fun deleteLocalParty(localParty: LocalPartyModel)
}