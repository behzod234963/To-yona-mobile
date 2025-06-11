package com.mr.anonym.data.implementations.remote

import androidx.annotation.Keep
import com.mr.anonym.data.instance.remote.FriendsApiService
import com.mr.anonym.domain.model.AddedByMeItem
import com.mr.anonym.domain.model.FriendsModel
import com.mr.anonym.domain.repository.remote.FriendsRepository
import com.mr.anonym.domain.response.DeleteFriendResponse
import retrofit2.Response

@Keep class FriendsRepositoryImpl(private val api: FriendsApiService): FriendsRepository {

    override suspend fun addFriend(friendID: Int): Response<AddedByMeItem> = api.addFriend(friendID)
    override suspend fun getAllMyFriends(): Response<FriendsModel> = api.getAllMyFriends()
    override suspend fun deleteFriend(friendID: Int): Response<DeleteFriendResponse> = api.deleteFriend(friendID)
}