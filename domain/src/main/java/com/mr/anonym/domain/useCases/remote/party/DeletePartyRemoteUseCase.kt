package com.mr.anonym.domain.useCases.remote.party

import android.util.Log
import androidx.annotation.Keep
import com.mr.anonym.domain.repository.remote.PartyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

@Keep class DeletePartyRemoteUseCase(private val repository: PartyRepository) {
    fun execute(id: Int): Flow<String> = flow {
        try {
            val response = repository.deleteParty(id)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(it)
                }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "DeleteEventUseCaseExecute: ${e.message}")
        }
    }
}