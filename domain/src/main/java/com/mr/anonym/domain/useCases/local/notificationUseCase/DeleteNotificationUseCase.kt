package com.mr.anonym.domain.useCases.local.notificationUseCase

import com.mr.anonym.domain.model.NotificationsModel
import com.mr.anonym.domain.repository.local.NotificationsRepository

class DeleteNotificationUseCase(private val notificationsRepository: NotificationsRepository) {
    suspend operator fun invoke(notification: NotificationsModel){
        notificationsRepository.deleteNotification(notification)
    }
}