package com.mr.anonym.domain.useCases.local.localCardUtilUseCase

import android.util.Log
import com.mr.anonym.domain.model.CardUtilModel
import com.mr.anonym.domain.repository.local.LocalCardUtilRepository

class InsertCardUtilUseCase(private val repository: LocalCardUtilRepository) {
    suspend fun execute(model: CardUtilModel){
        try {
            repository.insertColorIndex(model)
        }catch (e: Exception){
            Log.d("LocalLogging", "InsertCardUtilUseCaseExecute: ${e.message}")
        }
    }
}