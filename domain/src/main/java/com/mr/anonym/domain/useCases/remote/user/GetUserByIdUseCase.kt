package com.mr.anonym.domain.useCases.remote.user

import android.util.Log
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.repository.remote.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetUserByIdUseCase(private val repository: UserRepository) {
    fun execute(userID: Int): Flow<UserModelItem> = flow {
        try {
            val response = repository.getUserByID(userID)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(it)
                }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "GetUserByIdUseCaseExecute:${e.message} ")
        }
    }
}