package com.mr.anonym.data.implementations.local

import com.mr.anonym.data.instance.local.room.LocalFriendsDAO
import com.mr.anonym.domain.model.LocalFriendsModel
import com.mr.anonym.domain.repository.local.LocalFriendsRepository
import kotlinx.coroutines.flow.Flow

class LocalFriendsRepositoryImpl(private val dao: LocalFriendsDAO): LocalFriendsRepository {
    override suspend fun insertLocalFriend(localFriend: LocalFriendsModel) {
        dao.insertLocalFriend(localFriend)
    }

    override fun getLocalFriends(): Flow<List<LocalFriendsModel>> = dao.getLocalFriends()

    override suspend fun deleteLocalFriend(localFriend: LocalFriendsModel) {
        dao.deleteLocalFriend(localFriend)
    }
}