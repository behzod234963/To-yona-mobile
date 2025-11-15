package com.mr.anonym.domain.useCases.local.notificationUseCase

import android.util.Log
import androidx.annotation.Keep
import com.mr.anonym.domain.model.NotificationsModel
import com.mr.anonym.domain.repository.local.NotificationsRepository

@Keep class InsertNotificationUseCase(private val notificationsRepository: NotificationsRepository) {

    suspend operator fun invoke(notification: NotificationsModel){
        try {
            notificationsRepository.insertNotification(notification)
        }catch (e: Exception){
            Log.d("LocalLogging", "InsertNotificationUseCaseInvoke: ${e.message}")
        }
    }
}