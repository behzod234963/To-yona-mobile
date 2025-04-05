package com.mr.anonym.domain.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mr.anonym.domain.model.NotificationsModel
import kotlinx.coroutines.flow.Flow

interface NotificationsRepository {

    suspend fun insertNotification(notification: NotificationsModel)
    fun getNotifications(): Flow<List<NotificationsModel>>
    fun getNotificationByID(id: Int): Flow<NotificationsModel>
    suspend fun deleteNotification(notification: NotificationsModel)
    suspend fun clearNotifications(notifications: List<NotificationsModel>)
}