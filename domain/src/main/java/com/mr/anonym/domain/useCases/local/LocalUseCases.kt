package com.mr.anonym.domain.useCases.local

data class LocalUseCases(
    val insertNotificationUseCase:InsertNotificationUseCase,
    val getNotificationsUseCase:GetNotificationsUseCase,
    val getNotificationsByIDUseCase:GetNotificationsByIDUseCase,
    val deleteNotificationUseCase:DeleteNotificationUseCase,
    val clearNotificationsUseCase:ClearNotificationsUseCase,
    val insertCardUseCase:InsertCardUseCase,
    val getCardsUseCase:GetCardsUseCase,
    val getCardByIdUseCase:GetCardByIdUseCase,
    val deleteCardUseCase:DeleteCardUseCase,
    val updateActiveStatusUseCase:UpdateActiveStatusUseCase,
    val getActiveCardsUseCase:GetActiveCardsUseCase
)
