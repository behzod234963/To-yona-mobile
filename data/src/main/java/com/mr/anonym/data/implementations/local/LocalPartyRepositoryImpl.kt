package com.mr.anonym.data.implementations.local

import androidx.annotation.Keep
import com.mr.anonym.data.instance.local.room.LocalPartyDAO
import com.mr.anonym.domain.model.LocalPartyModel
import com.mr.anonym.domain.repository.local.LocalPartyRepository
import kotlinx.coroutines.flow.Flow

@Keep class LocalPartyRepositoryImpl (private val dao: LocalPartyDAO): LocalPartyRepository{
    override suspend fun insertLocalParty(localParty: LocalPartyModel) {
        dao.insertLocalParty(localParty)
    }

    override suspend fun insertAllParty(partyList: List<LocalPartyModel>) {
        dao.insertAllParty(partyList)
    }

    override fun getAllLocalParty(): Flow<List<LocalPartyModel>> = dao.getAllLocalParty()

    override suspend fun clearAllLocalParty(partyList: List<LocalPartyModel>) {
        dao.clearAllLocalParty(partyList)
    }

    override suspend fun deleteLocalParty(localParty: LocalPartyModel) {
        dao.deleteLocalParty(localParty)
    }
}