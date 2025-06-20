package com.mr.anonym.domain.useCases.local.localPartyUseCase

import androidx.annotation.Keep
import com.mr.anonym.domain.model.LocalPartyModel
import com.mr.anonym.domain.repository.local.LocalPartyRepository

@Keep
class InsertLocalPartyUseCase(private val repository: LocalPartyRepository) {
    suspend fun execute(localParty: LocalPartyModel){
        repository.insertLocalParty(localParty)
    }
}