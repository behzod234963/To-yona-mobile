package com.mr.anonym.data.implementations.local

import androidx.annotation.Keep
import com.mr.anonym.data.instance.local.room.LocalCardUtilDAO
import com.mr.anonym.domain.model.CardUtilModel
import com.mr.anonym.domain.repository.local.LocalCardUtilRepository
import kotlinx.coroutines.flow.Flow

@Keep
class LocalCardUtilRepositoryImpl(private val dao: LocalCardUtilDAO): LocalCardUtilRepository {
    override suspend fun insertColorIndex(model: CardUtilModel) {
        dao.insertColorIndex(model)
    }

    override fun getColorByID(id: Int): Flow<CardUtilModel> {
        return dao.getColorByID(id)
    }

    override suspend fun deleteCardUtil(model: CardUtilModel) {
        dao.deleteCardUtil(model)
    }
}