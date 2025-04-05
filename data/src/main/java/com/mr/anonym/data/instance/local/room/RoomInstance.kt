package com.mr.anonym.data.instance.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mr.anonym.domain.model.NotificationsModel

@Database(entities = [NotificationsModel::class], version = 1)
abstract class RoomInstance : RoomDatabase(){
    abstract val notificationsDAO: NotificationsDAO
    companion object{
        const val DB_NAME = "Notifications database"
    }
}