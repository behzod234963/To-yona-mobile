package com.mr.anonym.domain.useCases.remote.party

import androidx.annotation.Keep
import com.mr.anonym.domain.repository.remote.PartyRepository

@Keep class GetAllPartyUseCase(private val repository: PartyRepository) {
    fun execute() = repository.getAllParty()
}