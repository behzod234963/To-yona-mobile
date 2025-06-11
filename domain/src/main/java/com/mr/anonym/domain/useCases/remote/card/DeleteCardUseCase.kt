package com.mr.anonym.domain.useCases.remote.card

import android.util.Log
import androidx.annotation.Keep
import com.mr.anonym.domain.repository.remote.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Keep class DeleteCardUseCase(private val repository: CardRepository) {
    fun execute(cardID: Int): Flow<String> = flow{
        try {
            val response = repository.deleteCard(cardID)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(it)
                }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "DeleteCardUseCaseExecute: ${e.message}")
        }
    }
}