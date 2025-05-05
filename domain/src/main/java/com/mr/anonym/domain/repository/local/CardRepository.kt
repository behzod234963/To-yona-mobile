package com.mr.anonym.domain.repository.local

import com.mr.anonym.domain.model.CardModel
import kotlinx.coroutines.flow.Flow

interface CardRepository {

    suspend fun insertCard(card: CardModel)
    fun getCards(): Flow<List<CardModel>>
    fun getCardById(id: Int): Flow<CardModel>
    suspend fun deleteCard(card: CardModel)
}