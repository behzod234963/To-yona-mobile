package com.mr.anonym.domain.useCases.local.notificationUseCase

import android.util.Log
import androidx.annotation.Keep
import com.mr.anonym.domain.model.NotificationsModel
import com.mr.anonym.domain.repository.local.NotificationsRepository

@Keep class ClearNotificationsUseCase(private val notificationsRepository: NotificationsRepository) {
    suspend operator fun invoke(notifications: List<NotificationsModel>){
        try {
            notificationsRepository.clearNotifications(notifications)
        }catch (e: Exception){
            Log.d("LocalLogging", "ClearNotificationsUseCaseInvoke: ${e.message}")
        }
    }
}