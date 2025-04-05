package com.mr.anonym.domain.repository

import androidx.room.Query
import com.mr.anonym.domain.model.MonitoringModel
import kotlinx.coroutines.flow.Flow

interface MonitoringRepository {

    suspend fun insertExpense(expense: MonitoringModel)
    fun getExpensesByMonth(index: Int): Flow<List<MonitoringModel>>
    fun getExpensesByCard(card: String): Flow<List<MonitoringModel>>
}