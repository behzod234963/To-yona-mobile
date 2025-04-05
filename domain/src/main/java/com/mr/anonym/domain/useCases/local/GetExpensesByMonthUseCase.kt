package com.mr.anonym.domain.useCases.local

import com.mr.anonym.domain.model.MonitoringModel
import com.mr.anonym.domain.repository.MonitoringRepository
import kotlinx.coroutines.flow.Flow

class GetExpensesByMonthUseCase(private val monitoringRepository: MonitoringRepository) {

    operator fun invoke(month: Int): Flow<List<MonitoringModel>> = monitoringRepository.getExpensesByMonth(month)
}