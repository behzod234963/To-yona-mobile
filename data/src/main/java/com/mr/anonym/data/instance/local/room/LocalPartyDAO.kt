package com.mr.anonym.data.instance.local.room

import androidx.annotation.Keep
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mr.anonym.domain.model.LocalPartyModel
import kotlinx.coroutines.flow.Flow

@Dao
@Keep
interface LocalPartyDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLocalParty(localParty: LocalPartyModel)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllParty(partyList: List<LocalPartyModel>)

    @Query("SELECT * FROM localpartymodel")
    fun getAllLocalParty(): Flow<List<LocalPartyModel>>

    @Delete
    suspend fun clearAllLocalParty(partyList: List<LocalPartyModel>)

    @Delete
    suspend fun deleteLocalParty(localParty: LocalPartyModel)
}