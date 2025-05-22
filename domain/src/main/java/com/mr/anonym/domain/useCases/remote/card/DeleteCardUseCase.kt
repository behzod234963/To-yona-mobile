package com.mr.anonym.domain.useCases.remote.card

import android.util.Log
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.repository.remote.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DeleteCardUseCase(private val repository: CardRepository) {
    fun execute(cardID: Int,cardModel: CardModel): Flow<String> = flow{
        try {
            val response = repository.deleteCard(cardID,cardModel)
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