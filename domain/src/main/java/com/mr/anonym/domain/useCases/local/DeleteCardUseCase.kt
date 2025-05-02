package com.mr.anonym.domain.useCases.local

import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.repository.local.CardRepository

class DeleteCardUseCase(private val repository: CardRepository) {
    suspend operator fun invoke(card: CardModel){
        repository.deleteCard(card)
    }
}