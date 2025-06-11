package com.mr.anonym.domain.useCases.remote.party

import android.util.Log
import androidx.annotation.Keep
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.repository.remote.PartyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Keep class GetPartyByIdUseCase(private val repository: PartyRepository) {
    fun execute(id: Int): Flow<PartysItem> = flow {
        try {
            val response = repository.getPartyByID(id)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(it)
                }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "GetPartyByIdUseCaseExecute: ${e.message}")
        }
    }
}