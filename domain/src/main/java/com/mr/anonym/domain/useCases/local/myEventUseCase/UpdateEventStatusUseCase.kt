package com.mr.anonym.domain.useCases.local.myEventUseCase

import com.mr.anonym.domain.repository.local.MyEventsRepository

class UpdateEventStatusUseCase(private val repository: MyEventsRepository) {
    suspend fun execute(id: Int,status: Boolean){
        repository.updateEventStatus(id,status)
    }
}