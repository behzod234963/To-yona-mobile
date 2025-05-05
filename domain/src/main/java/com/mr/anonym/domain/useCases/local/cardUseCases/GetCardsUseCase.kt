package com.mr.anonym.domain.useCases.local.cardUseCases

import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.repository.local.CardRepository
import kotlinx.coroutines.flow.Flow

class GetCardsUseCase(private val repository: CardRepository) {
    operator fun invoke(): Flow<List<CardModel>> =
        repository.getCards()
}