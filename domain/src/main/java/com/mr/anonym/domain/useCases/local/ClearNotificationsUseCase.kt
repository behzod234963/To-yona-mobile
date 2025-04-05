package com.mr.anonym.domain.useCases.local

import com.mr.anonym.domain.model.NotificationsModel
import com.mr.anonym.domain.repository.NotificationsRepository

class ClearNotificationsUseCase(private val notificationsRepository: NotificationsRepository) {
    suspend operator fun invoke(notifications: List<NotificationsModel>){
        notificationsRepository.clearNotifications(notifications)
    }
}