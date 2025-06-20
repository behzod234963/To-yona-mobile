package com.mr.anonym.domain.useCases.local.localPartyUseCase

import android.util.Log
import androidx.annotation.Keep
import com.mr.anonym.domain.model.LocalPartyModel
import com.mr.anonym.domain.repository.local.LocalPartyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Keep class GetAllLocalPartyUseCase(private val repository: LocalPartyRepository) {
    fun execute(): Flow<List<LocalPartyModel>> = flow {
        try {
            repository.getAllLocalParty().collect {
                emit(it)
            }
        }catch (e: Exception){
            Log.d("LocalLogging", "GetAllLocalPartyUseCaseExecute: ${e.message}")
        }
    }
}