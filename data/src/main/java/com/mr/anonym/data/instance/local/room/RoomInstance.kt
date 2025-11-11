package com.mr.anonym.data.instance.local.room

import androidx.annotation.Keep
import androidx.room.Database
import androidx.room.RoomDatabase
import com.mr.anonym.domain.model.CardUtilModel
import com.mr.anonym.domain.model.LocalFriendsModel
import com.mr.anonym.domain.model.LocalPartyModel
import com.mr.anonym.domain.model.NotificationsModel

@Keep
@Database(entities = [NotificationsModel::class, LocalPartyModel::class, LocalFriendsModel::class, CardUtilModel::class], version = 1)
abstract class RoomInstance : RoomDatabase(){

    abstract val notificationsDAO: NotificationsDAO
    abstract val localPartyDAO: LocalPartyDAO
    abstract val localFriendsDAO: LocalFriendsDAO
    abstract val localCardUtilDAO: LocalCardUtilDAO

    companion object{
        const val DB_NAME = "universal_database"
    }
}