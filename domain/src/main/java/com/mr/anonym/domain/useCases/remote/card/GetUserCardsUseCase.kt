package com.mr.anonym.domain.useCases.remote.card

import android.util.Log
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.repository.remote.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserCardsUseCase(private val repository: CardRepository) {
    fun execute(userID: Int): Flow<List<CardModel>> = flow {
        try{
            val response = repository.getUserCards(userID)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(it.cardlist)
                }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "GetUserCardsUseCaseExecute: ${e.message}")
        }
    }
}