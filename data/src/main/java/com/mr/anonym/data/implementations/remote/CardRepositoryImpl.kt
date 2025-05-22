package com.mr.anonym.data.implementations.remote

import com.mr.anonym.data.instance.remote.CardApiService
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.repository.remote.CardRepository
import retrofit2.Response

class CardRepositoryImpl(private val api: CardApiService): CardRepository {
    override suspend fun addCard(userID: Int,cardModel: CardModel): Response<CardModel> = api.addCard(userID,cardModel)
    override suspend fun getAllCard(): Response<List<CardModel>> = api.getAllCard()
    override suspend fun getCardByID(cardID: Int): Response<CardModel> = api.getCardByID(cardID)
    override suspend fun updateCard(cardID: Int, cardModel: CardModel): Response<CardModel> = api.updateCard(cardID,cardModel)
    override suspend fun deleteCard(cardID: Int, cardModel: CardModel): Response<String> = api.deleteCard(cardID,cardModel)
    override suspend fun getUserCards(id: Int): Response<UserModelItem> = api.getUserCards(id)
}