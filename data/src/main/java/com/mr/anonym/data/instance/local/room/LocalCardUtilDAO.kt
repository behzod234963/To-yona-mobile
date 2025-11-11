package com.mr.anonym.data.instance.local.room

import androidx.annotation.Keep
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mr.anonym.domain.model.CardUtilModel
import kotlinx.coroutines.flow.Flow

@Dao
@Keep interface LocalCardUtilDAO {

    @Insert
    suspend fun insertColorIndex(model: CardUtilModel)

    @Query("SELECT * FROM cardutilmodel WHERE id = :id")
    fun getColorByID(id: Int): Flow<CardUtilModel>

    @Delete
    suspend fun deleteCardUtil(model: CardUtilModel)
}