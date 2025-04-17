package com.mr.anonym.domain.useCases.local

import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.repository.CardRepository
import kotlinx.coroutines.flow.Flow

class GetCardsUseCase(private val repository: CardRepository) {
    operator fun invoke(): Flow<List<CardModel>> =
        repository.getCards()
}