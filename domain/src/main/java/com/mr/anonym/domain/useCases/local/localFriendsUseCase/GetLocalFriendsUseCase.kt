package com.mr.anonym.domain.useCases.local.localFriendsUseCase

import android.util.Log
import com.mr.anonym.domain.model.LocalFriendsModel
import com.mr.anonym.domain.repository.local.LocalFriendsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetLocalFriendsUseCase(private val repository: LocalFriendsRepository) {
    fun execute(): Flow<List<LocalFriendsModel>> = flow {
        try {
            repository.getLocalFriends().collect {
                emit(it)
            }
        }catch (e: Exception){
            Log.d("LocalLogging", "GetLocalFriendsUseCaseExecute: ${e.message}")
        }
    }
}