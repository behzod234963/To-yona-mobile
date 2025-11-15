package com.mr.anonym.domain.useCases.local.notificationUseCase

import android.util.Log
import androidx.annotation.Keep
import com.mr.anonym.domain.model.NotificationsModel
import com.mr.anonym.domain.repository.local.NotificationsRepository

@Keep class DeleteNotificationUseCase(private val notificationsRepository: NotificationsRepository) {
    suspend operator fun invoke(notification: NotificationsModel){
        try {
            notificationsRepository.deleteNotification(notification)
        }catch (e: Exception){
            Log.d("LocalLogging", "DeleteNotificationUseCaseInvoke: ${e.message}")
        }
    }
}