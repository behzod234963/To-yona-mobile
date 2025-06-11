package com.mr.anonym.domain.useCases.remote.card

import android.util.Log
import androidx.annotation.Keep
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.repository.remote.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Keep class GetCardByIdUseCase(private val repository: CardRepository) {
    fun execute(cardID: Int): Flow<CardModel> = flow {
        try {
            val response = repository.getCardByID(cardID)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(it)
                }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "GetCardByIdUseCaseExecute: ${e.message}")
        }
    }
}