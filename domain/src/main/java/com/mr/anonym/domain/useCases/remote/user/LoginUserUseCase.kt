package com.mr.anonym.domain.useCases.remote.user

import android.util.Log
import com.mr.anonym.domain.repository.remote.UserRepository
import com.mr.anonym.domain.response.LoginRequest
import com.mr.anonym.domain.response.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginUserUseCase(private val repository: UserRepository) {
    fun execute(user: LoginRequest): Flow<LoginResponse> = flow {
        try {
            val response = repository.loginUser(user)
            if (response.isSuccessful){
                response.body()?.let {
                    emit( it )
                }
            }
        }catch ( e: Exception ){
            Log.d("NetworkLogging", "LoginUserUseCaseExecute: ${e.message}")
        }
    }
}