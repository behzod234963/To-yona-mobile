package com.mr.anonym.domain.useCases.local

import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.repository.local.CardRepository
import kotlinx.coroutines.flow.Flow

class GetActiveCardsUseCase(private val repository: CardRepository) {

    fun execute(): Flow<List<CardModel>> = repository.getActiveCards()
}