package com.mr.anonym.data.instance.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mr.anonym.domain.model.MyEventsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MyEventsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEvent(event: MyEventsModel)
    @Query("UPDATE myEvents SET eventStatus =:status WHERE id =:id")
    suspend fun updateEventStatus(id: Int,status: Boolean)
    @Delete
    suspend fun deleteEvent(event: MyEventsModel)
    @Query("SELECT * FROM myEvents")
    fun getEvents(): Flow<List<MyEventsModel>>
    @Query("SELECT * FROM myEvents WHERE id=:id")
    fun getEventByID(id: Int): Flow<MyEventsModel>
    @Query("SELECT * FROM myEvents WHERE eventStatus = 1")
    fun getActiveEvents(): Flow<List<MyEventsModel>>
}