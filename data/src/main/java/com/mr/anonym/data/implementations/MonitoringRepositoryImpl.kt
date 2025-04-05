package com.mr.anonym.data.implementations

import com.mr.anonym.data.instance.local.room.MonitoringDAO
import com.mr.anonym.domain.model.MonitoringModel
import com.mr.anonym.domain.repository.MonitoringRepository
import kotlinx.coroutines.flow.Flow

class MonitoringRepositoryImpl(private val monitoringDAO: MonitoringDAO): MonitoringRepository {

    override suspend fun insertExpense(expense: MonitoringModel) {
        monitoringDAO.insertExpense(expense)
    }
    override fun getExpensesByMonth(index: Int): Flow<List<MonitoringModel>> = monitoringDAO.getExpensesByMonth(index)
    override fun getExpensesByCard(card: String): Flow<List<MonitoringModel>> = monitoringDAO.getExpensesByCard(card)
}