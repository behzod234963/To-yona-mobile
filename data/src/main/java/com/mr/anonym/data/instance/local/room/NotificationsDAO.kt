package com.mr.anonym.data.instance.local.room

import androidx.annotation.Keep
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mr.anonym.domain.model.NotificationsModel
import kotlinx.coroutines.flow.Flow

@Dao
@Keep interface NotificationsDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notification: NotificationsModel)

    @Query("SELECT * FROM notifications")
    fun getNotifications(): Flow<List<NotificationsModel>>

    @Query("SELECT * FROM notifications WHERE id = :id")
    fun getNotificationByID(id: Int): Flow<NotificationsModel>

    @Delete
    suspend fun deleteNotification(notification: NotificationsModel)

    @Delete
    suspend fun clearNotifications(notifications: List<NotificationsModel>)
}