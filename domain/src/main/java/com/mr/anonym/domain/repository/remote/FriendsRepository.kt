package com.mr.anonym.domain.repository.remote

import com.mr.anonym.domain.model.AddedByMeItem
import com.mr.anonym.domain.model.FriendsModel
import com.mr.anonym.domain.response.DeleteFriendResponse
import retrofit2.Response

interface FriendsRepository {
    suspend fun addFriend(friendID: Int): Response<AddedByMeItem>
    suspend fun getAllMyFriends(): Response<FriendsModel>
    suspend fun deleteFriend(friendID: Int): Response<DeleteFriendResponse>
}