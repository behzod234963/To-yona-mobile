package com.mr.anonym.domain.useCases.local

import com.mr.anonym.domain.model.MonitoringModel
import com.mr.anonym.domain.repository.MonitoringRepository
import kotlinx.coroutines.flow.Flow

class GetExpensesByCardUseCase(private val monitoringRepository: MonitoringRepository) {

    operator fun invoke( card: String ): Flow<List<MonitoringModel>> = monitoringRepository.getExpensesByCard(card)
}