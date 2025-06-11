package com.mr.anonym.data.instance.remote

import androidx.annotation.Keep
import com.mr.anonym.domain.model.AddedByMeItem
import com.mr.anonym.domain.model.FriendsModel
import com.mr.anonym.domain.response.DeleteFriendResponse
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

@Keep interface FriendsApiService {

    @POST("/friend/add/friendid/{id}")
    suspend fun addFriend(
        @Path("id") friendID: Int
    ): Response<AddedByMeItem>

    @GET("/friend/getmy")
    suspend fun getAllMyFriends(): Response<FriendsModel>

    @DELETE("/friend/delete/friendid/{id}")
    suspend fun deleteFriend(
        @Path("id") friendID: Int
    ): Response<DeleteFriendResponse>
}