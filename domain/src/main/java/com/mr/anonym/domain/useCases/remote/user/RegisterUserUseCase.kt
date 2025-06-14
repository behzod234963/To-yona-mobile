package com.mr.anonym.domain.useCases.remote.user

import android.util.Log
import androidx.annotation.Keep
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.repository.remote.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Keep class RegisterUserUseCase(private val userRepository: UserRepository) {
    fun execute(user: UserModelItem): Flow<UserModelItem> = flow{
        try {
            val response = userRepository.registerUser(user)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(it)
                }
            }else{
                emit(
                    UserModelItem()
                )
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "RegisterUserUseCaseExecute: ${e.message}")
        }
    }
}