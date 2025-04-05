package com.mr.anonym.data.instance.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mr.anonym.domain.model.MonitoringModel
import kotlinx.coroutines.flow.Flow

@Dao
interface MonitoringDAO {

    @Insert
    suspend fun insertExpense(expense: MonitoringModel)

    @Query("SELECT * FROM monitoringentity WHERE monthIndex =:index")
    fun getExpensesByMonth(index: Int): Flow<List<MonitoringModel>>

    @Query("SELECT * FROM monitoringentity WHERE receiverCardNumber =:card")
    fun getExpensesByCard(card: String): Flow<List<MonitoringModel>>
}