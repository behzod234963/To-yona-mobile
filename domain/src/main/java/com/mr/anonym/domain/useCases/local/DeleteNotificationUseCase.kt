package com.mr.anonym.domain.useCases.local

import com.mr.anonym.domain.model.NotificationsModel
import com.mr.anonym.domain.repository.NotificationsRepository

class DeleteNotificationUseCase(private val notificationsRepository: NotificationsRepository) {
    suspend operator fun invoke(notification: NotificationsModel){
        notificationsRepository.deleteNotification(notification)
    }
}