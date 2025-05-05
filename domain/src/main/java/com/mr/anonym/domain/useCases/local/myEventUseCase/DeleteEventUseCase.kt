package com.mr.anonym.domain.useCases.local.myEventUseCase

import com.mr.anonym.domain.model.MyEventsModel
import com.mr.anonym.domain.repository.local.MyEventsRepository

class DeleteEventUseCase(private val repository: MyEventsRepository) {
    suspend fun execute(event: MyEventsModel){
        repository.deleteEvent(event)
    }
}