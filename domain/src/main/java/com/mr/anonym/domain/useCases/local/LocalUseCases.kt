package com.mr.anonym.domain.useCases.local

import androidx.annotation.Keep
import com.mr.anonym.domain.useCases.local.localPartyUseCase.ClearAllLocalPartyUseCase
import com.mr.anonym.domain.useCases.local.localPartyUseCase.DeleteLocalPartyUseCase
import com.mr.anonym.domain.useCases.local.localPartyUseCase.GetAllLocalPartyUseCase
import com.mr.anonym.domain.useCases.local.localPartyUseCase.InsertAllLocalPartyUseCase
import com.mr.anonym.domain.useCases.local.localPartyUseCase.InsertLocalPartyUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.ClearNotificationsUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.DeleteNotificationUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.GetNotificationsByIDUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.GetNotificationsUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.InsertNotificationUseCase

@Keep data class LocalUseCases(
    val insertLocalParty: InsertLocalPartyUseCase,
    val insertNotificationUseCase: InsertNotificationUseCase,
    val getNotificationsUseCase: GetNotificationsUseCase,
    val getNotificationsByIDUseCase: GetNotificationsByIDUseCase,
    val deleteNotificationUseCase: DeleteNotificationUseCase,
    val clearNotificationsUseCase: ClearNotificationsUseCase,
    val insertAllParty: InsertAllLocalPartyUseCase,
    val getAllLocalParty: GetAllLocalPartyUseCase,
    val clearAllParty: ClearAllLocalPartyUseCase,
    val deleteLocalParty: DeleteLocalPartyUseCase
)
