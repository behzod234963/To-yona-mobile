package com.mr.anonym.domain.useCases.local

import com.mr.anonym.domain.model.NotificationsModel
import com.mr.anonym.domain.repository.NotificationsRepository
import kotlinx.coroutines.flow.Flow

class GetNotificationsByIDUseCase(private val notificationsRepository: NotificationsRepository) {
    operator fun invoke(id: Int): Flow<NotificationsModel> = notificationsRepository.getNotificationByID(id)
}