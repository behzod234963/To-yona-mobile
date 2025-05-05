package com.mr.anonym.domain.useCases.local

import com.mr.anonym.domain.useCases.local.cardUseCases.DeleteCardUseCase
import com.mr.anonym.domain.useCases.local.cardUseCases.GetCardByIdUseCase
import com.mr.anonym.domain.useCases.local.cardUseCases.GetCardsUseCase
import com.mr.anonym.domain.useCases.local.cardUseCases.InsertCardUseCase
import com.mr.anonym.domain.useCases.local.myEventUseCase.DeleteEventUseCase
import com.mr.anonym.domain.useCases.local.myEventUseCase.GetActiveEventsUseCase
import com.mr.anonym.domain.useCases.local.myEventUseCase.GetEventByIdUseCase
import com.mr.anonym.domain.useCases.local.myEventUseCase.GetEventsUseCase
import com.mr.anonym.domain.useCases.local.myEventUseCase.InsertEventUseCase
import com.mr.anonym.domain.useCases.local.myEventUseCase.UpdateEventStatusUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.ClearNotificationsUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.DeleteNotificationUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.GetNotificationsByIDUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.GetNotificationsUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.InsertNotificationUseCase

data class LocalUseCases(
    val insertNotificationUseCase: InsertNotificationUseCase,
    val getNotificationsUseCase: GetNotificationsUseCase,
    val getNotificationsByIDUseCase: GetNotificationsByIDUseCase,
    val deleteNotificationUseCase: DeleteNotificationUseCase,
    val clearNotificationsUseCase: ClearNotificationsUseCase,
    val insertCardUseCase: InsertCardUseCase,
    val getCardsUseCase: GetCardsUseCase,
    val getCardByIdUseCase: GetCardByIdUseCase,
    val deleteCardUseCase: DeleteCardUseCase,

    val insertEventUseCase: InsertEventUseCase,
    val updateEventStatusUseCase: UpdateEventStatusUseCase,
    val deleteEventUseCase: DeleteEventUseCase,
    val getEventsUseCase: GetEventsUseCase,
    val getEventByIdUseCase: GetEventByIdUseCase,
    val getActiveEventsUseCase: GetActiveEventsUseCase
)
