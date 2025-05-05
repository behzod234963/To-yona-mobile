package com.mr.anonym.data.implementations.local

import com.mr.anonym.data.instance.local.room.MyEventsDAO
import com.mr.anonym.domain.model.MyEventsModel
import com.mr.anonym.domain.repository.local.MyEventsRepository
import kotlinx.coroutines.flow.Flow

class MyEventsRepositoryImpl(private val dao: MyEventsDAO): MyEventsRepository {

    override suspend fun insertEvent(event: MyEventsModel) {
        dao.insertEvent(event)
    }
    override suspend fun updateEventStatus(id: Int, status: Boolean) {
        dao.updateEventStatus(id,status)
    }
    override suspend fun deleteEvent(event: MyEventsModel) {
        dao.deleteEvent(event)
    }
    override fun getEvents(): Flow<List<MyEventsModel>> = dao.getEvents()
    override fun getEventByID(id: Int): Flow<MyEventsModel> = dao.getEventByID(id)
    override fun getActiveEvents(): Flow<List<MyEventsModel>> = dao.getActiveEvents()
}