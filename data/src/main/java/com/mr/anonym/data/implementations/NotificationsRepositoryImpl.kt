package com.mr.anonym.data.implementations

import com.mr.anonym.data.instance.local.room.NotificationsDAO
import com.mr.anonym.domain.model.NotificationsModel
import com.mr.anonym.domain.repository.NotificationsRepository
import kotlinx.coroutines.flow.Flow

class NotificationsRepositoryImpl(private val notificationsDAO: NotificationsDAO): NotificationsRepository {
    override suspend fun insertNotification(notification: NotificationsModel) {
        notificationsDAO.insertNotification(notification)
    }
    override fun getNotifications(): Flow<List<NotificationsModel>> = notificationsDAO.getNotifications()
    override fun getNotificationByID(id: Int): Flow<NotificationsModel> = notificationsDAO.getNotificationByID(id)
    override suspend fun deleteNotification(notification: NotificationsModel) {
        notificationsDAO.deleteNotification(notification)
    }
    override suspend fun clearNotifications(notifications: List<NotificationsModel>) {
        notificationsDAO.clearNotifications(notifications)
    }
}