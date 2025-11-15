package com.mr.anonym.domain.useCases.local.notificationUseCase

import android.util.Log
import androidx.annotation.Keep
import com.mr.anonym.domain.model.NotificationsModel
import com.mr.anonym.domain.repository.local.NotificationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@Keep class GetNotificationsUseCase(private val notificationsRepository: NotificationsRepository) {

    operator fun invoke(): Flow<List<NotificationsModel>> = flow {
        try {
            notificationsRepository.getNotifications().collect {
                emit(it)
            }
        }catch (e: Exception){
            Log.d("LocalLogging", "InsertNotificationUseCaseInvoke: ${e.message}")
        }
    }
}