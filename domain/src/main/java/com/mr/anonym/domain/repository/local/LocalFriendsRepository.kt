package com.mr.anonym.domain.repository.local

import androidx.room.Delete
import androidx.room.Query
import com.mr.anonym.domain.model.LocalFriendsModel
import kotlinx.coroutines.flow.Flow

interface LocalFriendsRepository {

    suspend fun insertLocalFriend(localFriend: LocalFriendsModel)

    fun getLocalFriends(): Flow<List<LocalFriendsModel>>

    suspend fun deleteLocalFriend(localFriend: LocalFriendsModel)
}