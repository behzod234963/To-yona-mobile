package com.mr.anonym.domain.useCases.local.notificationUseCase

import androidx.annotation.Keep
import com.mr.anonym.domain.model.NotificationsModel
import com.mr.anonym.domain.repository.local.NotificationsRepository
import kotlinx.coroutines.flow.Flow

@Keep class GetNotificationsUseCase(private val notificationsRepository: NotificationsRepository) {

    operator fun invoke(): Flow<List<NotificationsModel>> = notificationsRepository.getNotifications()
}