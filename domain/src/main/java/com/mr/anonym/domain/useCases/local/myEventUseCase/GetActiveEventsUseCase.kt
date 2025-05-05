package com.mr.anonym.domain.useCases.local.myEventUseCase

import com.mr.anonym.domain.model.MyEventsModel
import com.mr.anonym.domain.repository.local.MyEventsRepository
import kotlinx.coroutines.flow.Flow

class GetActiveEventsUseCase(private val repository: MyEventsRepository) {
    fun execute(): Flow<List<MyEventsModel>> = repository.getActiveEvents()
}