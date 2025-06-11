package com.mr.anonym.domain.useCases.local.notificationUseCase

import androidx.annotation.Keep
import com.mr.anonym.domain.model.NotificationsModel
import com.mr.anonym.domain.repository.local.NotificationsRepository

@Keep class InsertNotificationUseCase(private val notificationsRepository: NotificationsRepository) {

    suspend operator fun invoke(notification: NotificationsModel){
        notificationsRepository.insertNotification(notification)
    }
}