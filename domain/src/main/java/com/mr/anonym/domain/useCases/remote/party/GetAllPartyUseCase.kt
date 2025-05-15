package com.mr.anonym.domain.useCases.remote.party

import com.mr.anonym.domain.repository.remote.PartyRepository

class GetAllPartyUseCase(private val repository: PartyRepository) {
    fun execute() = repository.getAllParty()
}