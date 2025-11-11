package com.mr.anonym.domain.useCases.local.localCardUtilUseCase

import android.util.Log
import com.mr.anonym.domain.model.CardUtilModel
import com.mr.anonym.domain.repository.local.LocalCardUtilRepository

class DeleteCardUtilUseCase(private val repository: LocalCardUtilRepository) {
    suspend fun execute(model: CardUtilModel){
        try {
            repository.deleteCardUtil(model)
        }catch (e: Exception){
            Log.d("LocalLogging", "DeleteCardUtilUseCaseExecute: ${e.message}")
        }
    }
}