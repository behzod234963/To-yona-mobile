package com.mr.anonym.domain.useCases.local.notificationUseCase

import com.mr.anonym.domain.model.NotificationsModel
import com.mr.anonym.domain.repository.local.NotificationsRepository

class ClearNotificationsUseCase(private val notificationsRepository: NotificationsRepository) {
    suspend operator fun invoke(notifications: List<NotificationsModel>){
        notificationsRepository.clearNotifications(notifications)
    }
}