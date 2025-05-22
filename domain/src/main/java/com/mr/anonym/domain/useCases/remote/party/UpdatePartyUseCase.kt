package com.mr.anonym.domain.useCases.remote.party

import android.util.Log
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.repository.remote.PartyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdatePartyUseCase(private val repository: PartyRepository) {
    fun execute(partyID: Int,partyModel: PartysItem): Flow<Boolean> = flow {
        try {
            val response = repository.updateParty(partyID,partyModel)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(it)
                }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "UpdatePartyUseCaseExecute: ${e.message}")
        }
    }
}