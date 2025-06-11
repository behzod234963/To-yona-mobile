package com.mr.anonym.domain.repository.remote

import androidx.annotation.Keep
import com.mr.anonym.domain.model.AddedByMeItem
import com.mr.anonym.domain.model.FriendsModel
import com.mr.anonym.domain.response.DeleteFriendResponse
import retrofit2.Response

@Keep interface FriendsRepository {
    suspend fun addFriend(friendID: Int): Response<AddedByMeItem>
    suspend fun getAllMyFriends(): Response<FriendsModel>
    suspend fun deleteFriend(friendID: Int): Response<DeleteFriendResponse>
}