package com.mr.anonym.data.implementations

import com.mr.anonym.data.instance.local.room.CardDAO
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow

class CardRepositoryImpl(private val cardDAO: CardDAO) : CardRepository {

    override suspend fun insertCard(card: CardModel) {
        cardDAO.insertCard(card)
    }
    override fun getCards(): Flow<List<CardModel>> = cardDAO.getCards()
    override fun getCardById(id: Int): Flow<CardModel> = cardDAO.getCardById(id)
    override suspend fun deleteCard(card: CardModel) {
        cardDAO.deleteCard(card)
    }
}