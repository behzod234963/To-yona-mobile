package com.mr.anonym.domain.useCases.local.localFriendsUseCase

import android.util.Log
import com.mr.anonym.domain.model.LocalFriendsModel
import com.mr.anonym.domain.repository.local.LocalFriendsRepository

class InsertLocalFriendUseCase(private val repository: LocalFriendsRepository) {

    suspend fun execute(localFriend: LocalFriendsModel){
        try {
            repository.insertLocalFriend(localFriend)
        }catch (e: Exception){
            Log.d("LocalLogging", "InsertLocalFriendUseCaseExecute: ${e.message}")
        }
    }
}