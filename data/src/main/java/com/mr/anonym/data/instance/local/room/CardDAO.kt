package com.mr.anonym.data.instance.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mr.anonym.domain.model.CardModel
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: CardModel)

    @Query("SELECT * FROM cardModel")
    fun getCards(): Flow<List<CardModel>>

    @Query("SELECT * FROM cardModel WHERE id =:id ")
    fun getCardById(id: Int): Flow<CardModel>

    @Delete
    suspend fun deleteCard(card: CardModel)
}