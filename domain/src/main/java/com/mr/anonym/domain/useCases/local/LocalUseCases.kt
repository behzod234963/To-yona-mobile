package com.mr.anonym.domain.useCases.local

data class LocalUseCases(
    val insertNotificationUseCase:InsertNotificationUseCase,
    val getNotificationsUseCase:GetNotificationsUseCase,
    val getNotificationsByIDUseCase:GetNotificationsByIDUseCase,
    val deleteNotificationUseCase:DeleteNotificationUseCase,
    val clearNotificationsUseCase:ClearNotificationsUseCase
)
