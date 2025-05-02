package com.mr.anonym.domain.useCases.local

import com.mr.anonym.domain.repository.local.CardRepository

class UpdateActiveStatusUseCase(private val repository: CardRepository) {

    suspend fun execute(id: Int,status: Boolean){
        repository.updateActiveStatus(id,status)
    }
}