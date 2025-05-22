package com.mr.anonym.domain.useCases.remote.party

import android.util.Log
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.repository.remote.PartyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserPartiesUseCase(private val repository: PartyRepository) {
    fun execute(id: Int): Flow<List<PartysItem>> = flow {
        try {
            val response = repository.getUserParties(id)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(it.partyList)
                }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "GetUserPartiesUseCaseExecute: ${e.message}")
        }
    }
}