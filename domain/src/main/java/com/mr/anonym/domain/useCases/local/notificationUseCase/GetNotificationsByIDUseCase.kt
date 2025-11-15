package com.mr.anonym.domain.useCases.local.notificationUseCase

import android.util.Log
import androidx.annotation.Keep
import com.mr.anonym.domain.model.NotificationsModel
import com.mr.anonym.domain.repository.local.NotificationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Keep class GetNotificationsByIDUseCase(private val notificationsRepository: NotificationsRepository) {
    operator fun invoke(id: Int): Flow<NotificationsModel> = flow {
        try {
            notificationsRepository.getNotificationByID(id).collect {
                emit(it)
            }
        }catch (e: Exception){
            Log.d("LocalLogging", "GetNotificationsByIDUseCaseInvoke: ${e.message}")
        }
    }
}