package com.mr.anonym.toyonamobile.presentation.state

import com.mr.anonym.domain.model.CardModel
import com.mr.anonym.domain.model.FriendsModel
import com.mr.anonym.domain.model.MyEventsModel
import com.mr.anonym.domain.model.NotificationsModel

data class ListState (
    val notifications: List<NotificationsModel> = emptyList(),
    val cards: List<CardModel> = emptyList(),
    val events: List<MyEventsModel> = emptyList(),
    val contacts: List<FriendsModel> = emptyList()
)