package com.mr.anonym.domain.useCases.remote.party

import android.util.Log
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.repository.remote.PartyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserPartiesUseCase(private val repository: PartyRepository) {
    fun execute(userID: Int): Flow<List<PartysItem>> = flow {
        try {
            val response = repository.getUserParties(userID)
            if (response.isSuccessful){
                response.body()?.let {
                    Log.d("NetworkLogging", "GetUserPartiesUseCaseExecute: ${it.partylist.size}")
                    emit(it.partylist)
                }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "GetUserPartiesUseCaseExecute: ${e.message}")
        }
    }
}