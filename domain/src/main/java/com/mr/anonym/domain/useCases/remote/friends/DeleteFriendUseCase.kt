package com.mr.anonym.domain.useCases.remote.friends

import android.util.Log
import androidx.annotation.Keep
import com.mr.anonym.domain.repository.remote.FriendsRepository
import com.mr.anonym.domain.response.DeleteFriendResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Keep class DeleteFriendUseCase(private val repository: FriendsRepository) {
    fun execute(friendID: Int): Flow<DeleteFriendResponse> = flow {
        try {
            val response = repository.deleteFriend(friendID)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(it)
                }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "DeleteFriendUseCaseExecute: ${e.message}")
        }
    }
}