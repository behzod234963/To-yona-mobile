package com.mr.anonym.data.instance.local.room

import androidx.annotation.Keep
import androidx.room.Database
import androidx.room.RoomDatabase
import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.model.LocalFriendsModel
import com.mr.anonym.domain.model.LocalPartyModel
import com.mr.anonym.domain.model.NotificationsModel

@Keep
@Database(entities = [NotificationsModel::class, LocalPartyModel::class, LocalFriendsModel::class], version = 1)
abstract class RoomInstance : RoomDatabase(){

    abstract val notificationsDAO: NotificationsDAO
    abstract val localPartyDAO: LocalPartyDAO
    abstract val localFriendsDAO: LocalFriendsDAO

    companion object{
        const val DB_NAME = "universal_database"
    }
}