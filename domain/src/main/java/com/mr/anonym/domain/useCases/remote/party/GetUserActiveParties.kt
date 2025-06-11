package com.mr.anonym.domain.useCases.remote.party

import android.util.Log
import androidx.annotation.Keep
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.model.UserModelItem
import com.mr.anonym.domain.repository.remote.PartyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Keep class GetUserActiveParties(private val repository: PartyRepository) {
    fun execute(userID: Int): Flow<List<PartysItem>> = flow {
        try {
            val response = repository.getUserParties(userID)
            if (response.isSuccessful){
                response.body()?.let { user->
                    emit(user.partylist.filter { it.status })
                }
            }
        }catch (e: Exception){
            Log.d("NetworkLogging", "GetUserActivePartiesExecute: ${e.message}")
        }
    }
}