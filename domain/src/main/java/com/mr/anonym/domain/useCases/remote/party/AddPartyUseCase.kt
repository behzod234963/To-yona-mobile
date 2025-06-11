package com.mr.anonym.domain.useCases.remote.party

import android.util.Log
import androidx.annotation.Keep
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.repository.remote.PartyRepository
import com.mr.anonym.domain.response.AddPartyResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Keep class AddPartyUseCase (private val repository: PartyRepository){
    fun execute(partyModel: PartysItem): Flow<AddPartyResponse> = flow {
        try {
            val response = repository.addParty(partyModel)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(it)
                }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "AddPartyUseCaseExecute: ${e.message}")
        }
    }
}