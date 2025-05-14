package com.mr.anonym.domain.useCases.remote

import android.util.Log
import com.mr.anonym.domain.model.UserModel
import com.mr.anonym.domain.repository.remote.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RegisterUserUseCase(private val userRepository: UserRepository) {
    fun execute(user: UserModel): Flow<UserModel> = flow{
        try {

            val response = userRepository.registerUser(user)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(it)
                }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "RegisterUserUseCaseExecute: ${e.message}")
        }
    }
}