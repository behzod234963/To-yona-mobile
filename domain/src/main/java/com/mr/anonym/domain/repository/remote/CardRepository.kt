package com.mr.anonym.domain.repository.remote

import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.model.UserModelItem
import retrofit2.Response

interface CardRepository {
    suspend fun addCard(userID: Int,cardModel: CardModel): Response<CardModel>
    suspend fun getAllCard(): Response<List<CardModel>>
    suspend fun getCardByID(cardID: Int): Response<CardModel>
    suspend fun updateCard(cardID: Int,cardModel: CardModel): Response<CardModel>
    suspend fun deleteCard(cardID: Int, cardModel: CardModel): Response<String>
    suspend fun getUserCards(id: Int): Response<UserModelItem>
}