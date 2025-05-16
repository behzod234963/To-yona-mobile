package com.mr.anonym.toyonamobile.presentation.state

import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.model.FriendsModel
import com.mr.anonym.domain.model.NotificationsModel
import com.mr.anonym.domain.model.PartysItem
import com.mr.anonym.domain.model.UserModelItem

data class ListState (
    val notifications: List<NotificationsModel> = emptyList(),
    val cards: List<CardModel> = emptyList(),
    val contacts: List<FriendsModel> = emptyList(),
    val parties: List<PartysItem> = emptyList(),
    val users: List<UserModelItem> = emptyList()
)