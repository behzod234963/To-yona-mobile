package com.mr.anonym.domain.repository.local

import com.mr.anonym.domain.model.CardUtilModel
import kotlinx.coroutines.flow.Flow

interface LocalCardUtilRepository {

    suspend fun insertColorIndex(model: CardUtilModel)
    fun getColorByID(id: Int): Flow<CardUtilModel>
    suspend fun deleteCardUtil(model: CardUtilModel)
}