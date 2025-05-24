package com.mr.anonym.domain.useCases.remote.user

import android.util.Log
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.repository.remote.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UpdateUserUseCase(private val repository: UserRepository) {
    fun execute(user: UserModelItem): Flow<UserModelItem> = flow {
        try{
            val response = repository.updateUser(user)
            if (response.isSuccessful){
                response.body()?.let {
                   emit(it)
                }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "UpdateUserUseCaseExecute: ${e.message}")
        }
    }
}