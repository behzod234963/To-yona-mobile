package com.mr.anonym.domain.useCases.remote.friends

import android.util.Log
import com.mr.anonym.domain.model.AddedByMeItem
import com.mr.anonym.domain.repository.remote.FriendsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

class AddFriendUseCase(private val friendsRepository: FriendsRepository) {
    fun execute(friendsID: Int): Flow<AddedByMeItem> = flow {
        try {
            val response = friendsRepository.addFriend(friendsID)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(it)
                }
            }
        } catch (e: Exception) {
            Log.d("NetworkLogging", "AddFriendUseCaseExecute: ${e.message}")
        }
    }
}