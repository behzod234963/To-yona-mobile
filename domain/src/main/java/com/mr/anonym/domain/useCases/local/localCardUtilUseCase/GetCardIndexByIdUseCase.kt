package com.mr.anonym.domain.useCases.local.localCardUtilUseCase

import android.util.Log
import com.mr.anonym.domain.model.CardUtilModel
import com.mr.anonym.domain.repository.local.LocalCardUtilRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCardIndexByIdUseCase(private val repository: LocalCardUtilRepository) {

    fun execute(id: Int): Flow<CardUtilModel> = flow{
        try {
            repository.getColorByID(id).collect {
                emit(it)
            }
        }catch (e: Exception){
            Log.d("LocalLogging", "GetCardIndexByIdUseCaseExecute: ${e.message}")
        }
    }
}