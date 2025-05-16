package com.mr.anonym.domain.useCases.remote.user

import android.util.Log
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.repository.remote.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SearchUserUseCase(private val repository: UserRepository) {
    fun execute(searchText: String): Flow<List<UserModelItem>> = flow{
        try{
            val response = repository.searchUser(searchText)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(it)
                }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "SearchUserUseCaseExecute: ${e.message}")
        }
    }
}