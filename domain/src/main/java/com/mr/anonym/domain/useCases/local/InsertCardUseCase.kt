package com.mr.anonym.domain.useCases.local

import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.repository.CardRepository

class InsertCardUseCase(private val repository: CardRepository) {
    suspend operator fun invoke(card: CardModel){
        repository.insertCard(card)
    }
}