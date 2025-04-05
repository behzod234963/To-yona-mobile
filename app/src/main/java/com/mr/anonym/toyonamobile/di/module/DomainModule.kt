package com.mr.anonym.toyonamobile.di.module

import com.mr.anonym.domain.repository.MonitoringRepository
import com.mr.anonym.domain.repository.NotificationsRepository
import com.mr.anonym.domain.useCases.local.ClearNotificationsUseCase
import com.mr.anonym.domain.useCases.local.DeleteNotificationUseCase
import com.mr.anonym.domain.useCases.local.GetExpensesByCardUseCase
import com.mr.anonym.domain.useCases.local.GetExpensesByMonthUseCase
import com.mr.anonym.domain.useCases.local.GetNotificationsByIDUseCase
import com.mr.anonym.domain.useCases.local.GetNotificationsUseCase
import com.mr.anonym.domain.useCases.local.InsertExpenseUseCase
import com.mr.anonym.domain.useCases.local.InsertNotificationUseCase
import com.mr.anonym.domain.useCases.local.LocalUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideUseCases(
        notificationsRepository: NotificationsRepository,
        monitoringRepository: MonitoringRepository
    ): LocalUseCases =
        LocalUseCases(
            insertNotificationUseCase = InsertNotificationUseCase(notificationsRepository),
            getNotificationsUseCase = GetNotificationsUseCase(notificationsRepository),
            getNotificationsByIDUseCase = GetNotificationsByIDUseCase(notificationsRepository),
            deleteNotificationUseCase = DeleteNotificationUseCase(notificationsRepository),
            clearNotificationsUseCase = ClearNotificationsUseCase(notificationsRepository),
            insertExpenseUseCase = InsertExpenseUseCase(monitoringRepository),
            getExpenseByMonthUseCase = GetExpensesByMonthUseCase(monitoringRepository),
            getExpensesByCardUseCase = GetExpensesByCardUseCase(monitoringRepository),
        )
}