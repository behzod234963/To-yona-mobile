package com.mr.anonym.data.instance.local.room

import androidx.annotation.Keep
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mr.anonym.domain.model.LocalFriendsModel
import kotlinx.coroutines.flow.Flow

@Keep
@Dao
interface LocalFriendsDAO {

    @Insert
    suspend fun insertLocalFriend(localFriend: LocalFriendsModel)

    @Query("SELECT * FROM localfriendsmodel")
    fun getLocalFriends(): Flow<List<LocalFriendsModel>>

    @Delete
    suspend fun deleteLocalFriend(localFriend: LocalFriendsModel)
}