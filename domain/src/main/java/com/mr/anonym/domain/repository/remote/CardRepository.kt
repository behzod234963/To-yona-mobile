package com.mr.anonym.domain.repository.remote

import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.model.UserModelItem
import retrofit2.Response

interface CardRepository {
    suspend fun addCard(cardModel: CardModel): Response<CardModel>
    suspend fun getAllCard(): Response<List<CardModel>>
    suspend fun getCardByID(cardID: Int): Response<CardModel>
    suspend fun updateCard(cardID: Int,cardModel: CardModel): Response<CardModel>
    suspend fun deleteCard(cardID: Int): Response<String>
    suspend fun getUserCards(userID: Int): Response<UserModelItem>
}