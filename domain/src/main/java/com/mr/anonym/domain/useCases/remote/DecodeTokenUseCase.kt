package com.mr.anonym.domain.useCases.remote

import android.util.Log
import com.mr.anonym.domain.repository.remote.UserRepository
import com.mr.anonym.domain.response.DecodeTokenResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DecodeTokenUseCase(private val repository: UserRepository) {
    fun execute(accessToken: String): Flow<DecodeTokenResponse> = flow {
        try {
            val response = repository.decodeToken(accessToken)
            if (response.isSuccessful){
                response.body()?.let { emit(it) }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "DecodeTokenUseCaseExecute: ${e.message}")
        }
    }
}