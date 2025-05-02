package com.mr.anonym.domain.repository.local

import com.mr.anonym.domain.model.CardModel
import kotlinx.coroutines.flow.Flow

interface CardRepository {

    suspend fun insertCard(card: CardModel)
    fun getCards(): Flow<List<CardModel>>
    fun getCardById(id: Int): Flow<CardModel>
    fun getActiveCards(): Flow<List<CardModel>>
    suspend fun updateActiveStatus(id: Int,status: Boolean)
    suspend fun deleteCard(card: CardModel)
}