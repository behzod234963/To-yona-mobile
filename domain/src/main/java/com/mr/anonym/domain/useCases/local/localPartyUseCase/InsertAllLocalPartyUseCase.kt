package com.mr.anonym.domain.useCases.local.localPartyUseCase

import android.util.Log
import androidx.annotation.Keep
import com.mr.anonym.domain.model.LocalPartyModel
import com.mr.anonym.domain.repository.local.LocalPartyRepository

@Keep class InsertAllLocalPartyUseCase(private val repository: LocalPartyRepository) {
    suspend fun execute(partyList: List<LocalPartyModel>){
        try {
            repository.insertAllParty(partyList)
        }catch (e: Exception){
            Log.d("LocalLogging", "InsertAllLocalPartyUseCaseExecute: ${e.message}")
        }
    }
}