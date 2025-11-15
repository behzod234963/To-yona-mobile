package com.mr.anonym.domain.useCases.local.localPartyUseCase

import android.util.Log
import androidx.annotation.Keep
import com.mr.anonym.domain.model.LocalPartyModel
import com.mr.anonym.domain.repository.local.LocalPartyRepository

@Keep
class DeleteLocalPartyUseCase(private val repository: LocalPartyRepository) {
    suspend fun execute(localParty: LocalPartyModel){
        try {
            repository.deleteLocalParty(localParty)
        }catch (e: Exception){
            Log.d("LocalLogging", "DeleteLocalPartyUseCaseExecute: ${e.message}")
        }
    }
}