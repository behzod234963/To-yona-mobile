package com.mr.anonym.domain.useCases.local

import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.repository.local.CardRepository
import kotlinx.coroutines.flow.Flow

class GetCardByIdUseCase(private val repository: CardRepository) {
    operator fun invoke(id: Int): Flow<CardModel> =
        repository.getCardById(id)
}