package com.mr.anonym.domain.useCases.remote.user

import android.util.Log
import androidx.annotation.Keep
import com.mr.anonym.domain.repository.remote.UserRepository
import com.mr.anonym.domain.response.DeleteUserResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.cancel
import kotlinx.coroutines.flow.flow

@Keep class DeleteUserUseCase(private val repository: UserRepository) {
    fun execute(): Flow<DeleteUserResponse> = flow {
        try {
            val response = repository.deleteUser()
            if (response.isSuccessful){
                response.body()?.let { emit(it) }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "DeleteUserUseCaseExecute: ${e.message}")
        }
    }
}