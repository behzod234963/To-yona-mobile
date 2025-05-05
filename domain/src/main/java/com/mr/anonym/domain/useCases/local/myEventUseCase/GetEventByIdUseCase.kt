package com.mr.anonym.domain.useCases.local.myEventUseCase

import com.mr.anonym.domain.model.MyEventsModel
import com.mr.anonym.domain.repository.local.MyEventsRepository
import kotlinx.coroutines.flow.Flow

class GetEventByIdUseCase(private val repository: MyEventsRepository) {
    fun execute(id:Int): Flow<MyEventsModel> = repository.getEventByID(id)
}