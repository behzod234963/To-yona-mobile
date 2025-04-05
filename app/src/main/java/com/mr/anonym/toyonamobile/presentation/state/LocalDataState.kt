package com.mr.anonym.toyonamobile.presentation.state

import com.mr.anonym.domain.model.NotificationsModel

data class LocalDataState (
    val notifications: List<NotificationsModel> = emptyList()
)