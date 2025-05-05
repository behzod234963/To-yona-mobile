package com.mr.anonym.domain.repository.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mr.anonym.domain.model.MyEventsModel
import kotlinx.coroutines.flow.Flow

interface MyEventsRepository {

    suspend fun insertEvent(event: MyEventsModel)
    suspend fun updateEventStatus(id: Int,status: Boolean)
    suspend fun deleteEvent(event: MyEventsModel)
    fun getEvents(): Flow<List<MyEventsModel>>
    fun getEventByID(id: Int): Flow<MyEventsModel>
    fun getActiveEvents(): Flow<List<MyEventsModel>>
}