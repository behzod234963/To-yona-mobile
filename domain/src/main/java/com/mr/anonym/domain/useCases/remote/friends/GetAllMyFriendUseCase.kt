package com.mr.anonym.domain.useCases.remote.friends

import android.util.Log
import androidx.annotation.Keep
import com.mr.anonym.domain.model.AddedByMeItem
import com.mr.anonym.domain.repository.remote.FriendsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Keep class GetAllMyFriendUseCase(private val friendsRepository: FriendsRepository) {
    fun execute(): Flow<List<AddedByMeItem>> = flow {
        try {
            val response = friendsRepository.getAllMyFriends()
            if(response.isSuccessful){
                response.body()?.let {
                    emit(it.addedByMe)
                }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "GetAllMyFriendUseCaseExecute: ${e.message}")
        }
    }
}