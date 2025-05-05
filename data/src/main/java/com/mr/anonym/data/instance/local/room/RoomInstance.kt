package com.mr.anonym.data.instance.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.model.MonitoringModel
import com.mr.anonym.domain.model.MyEventsModel
import com.mr.anonym.domain.model.NotificationsModel

@Database(entities = [NotificationsModel::class, CardModel::class, MyEventsModel::class], version = 1)
abstract class RoomInstance : RoomDatabase(){

    abstract val notificationsDAO: NotificationsDAO
    abstract val cardDAO: CardDAO
    abstract val myEventsDAO: MyEventsDAO

    companion object{
        const val DB_NAME = "universal_database"
    }
}