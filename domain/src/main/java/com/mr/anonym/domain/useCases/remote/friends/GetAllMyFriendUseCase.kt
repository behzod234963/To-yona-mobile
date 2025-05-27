package com.mr.anonym.domain.useCases.remote.friends

import android.util.Log
import com.mr.anonym.domain.model.FriendsModel
import com.mr.anonym.domain.repository.remote.FriendsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetAllMyFriendUseCase(private val friendsRepository: FriendsRepository) {
    fun execute(): Flow<FriendsModel> = flow {
        try {
            val response = friendsRepository.getAllMyFriends()
            if(response.isSuccessful){
                response.body()?.let {
                    emit(it)
                }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "GetAllMyFriendUseCaseExecute: ${e.message}")
        }
    }
}