package com.mr.anonym.domain.useCases.local.myEventUseCase

import com.mr.anonym.domain.model.MyEventsModel
import com.mr.anonym.domain.repository.local.MyEventsRepository

class InsertEventUseCase(private val repository: MyEventsRepository) {
    suspend fun execute(event: MyEventsModel){
        repository.insertEvent(event)
    }
}