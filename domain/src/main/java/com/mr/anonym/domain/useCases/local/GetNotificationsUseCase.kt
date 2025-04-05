package com.mr.anonym.domain.useCases.local

import com.mr.anonym.domain.model.NotificationsModel
import com.mr.anonym.domain.repository.NotificationsRepository
import kotlinx.coroutines.flow.Flow

class GetNotificationsUseCase(private val notificationsRepository: NotificationsRepository) {

    operator fun invoke(): Flow<List<NotificationsModel>> = notificationsRepository.getNotifications()
}