package com.mr.anonym.domain.useCases.local.cardUseCases

import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.repository.local.CardRepository

class InsertCardUseCase(private val repository: CardRepository) {
    suspend operator fun invoke(card: CardModel){
        repository.insertCard(card)
    }
}