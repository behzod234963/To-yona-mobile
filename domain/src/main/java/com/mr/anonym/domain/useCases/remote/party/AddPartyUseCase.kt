package com.mr.anonym.domain.useCases.remote.party

import android.util.Log
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.repository.remote.PartyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddPartyUseCase (private val repository: PartyRepository){
    fun execute(userId: Int,partyModel: PartysItem): Flow<Boolean> = flow {
        try {
            val response = repository.addParty(userId,partyModel)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(it)
                }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "AddEventUseCaseExecute: ${e.message}")
        }
    }
}