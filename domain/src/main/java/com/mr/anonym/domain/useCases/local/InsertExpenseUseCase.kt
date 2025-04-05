package com.mr.anonym.domain.useCases.local

import com.mr.anonym.domain.model.MonitoringModel
import com.mr.anonym.domain.repository.MonitoringRepository

class InsertExpenseUseCase(private val monitoringRepository: MonitoringRepository) {

    suspend operator fun invoke(expense: MonitoringModel){
        monitoringRepository.insertExpense(expense)
    }
}