package com.mr.anonym.domain.useCases.remote.card

import android.util.Log
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.repository.remote.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllCardUseCase(private val repository: CardRepository) {
    fun execute(): Flow<List<CardModel>> = flow {
        try {
            val response = repository.getAllCard()
            if (response.isSuccessful){
                response.body()?.let {
                    emit(it)
                }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "GetAllCardUseCaseExecute: ${e.message}")
        }
    }
}