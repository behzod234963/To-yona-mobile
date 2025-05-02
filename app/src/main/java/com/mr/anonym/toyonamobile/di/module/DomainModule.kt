package com.mr.anonym.toyonamobile.di.module

import com.mr.anonym.domain.repository.local.CardRepository
import com.mr.anonym.domain.repository.local.NotificationsRepository
import com.mr.anonym.domain.useCases.local.ClearNotificationsUseCase
import com.mr.anonym.domain.useCases.local.DeleteCardUseCase
import com.mr.anonym.domain.useCases.local.DeleteNotificationUseCase
import com.mr.anonym.domain.useCases.local.GetActiveCardsUseCase
import com.mr.anonym.domain.useCases.local.GetCardByIdUseCase
import com.mr.anonym.domain.useCases.local.GetCardsUseCase
import com.mr.anonym.domain.useCases.local.GetNotificationsByIDUseCase
import com.mr.anonym.domain.useCases.local.GetNotificationsUseCase
import com.mr.anonym.domain.useCases.local.InsertCardUseCase
import com.mr.anonym.domain.useCases.local.InsertNotificationUseCase
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.domain.useCases.local.UpdateActiveStatusUseCase
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
        cardRepository: CardRepository,
    ): LocalUseCases =
        LocalUseCases(
            insertNotificationUseCase = InsertNotificationUseCase(notificationsRepository),
            getNotificationsUseCase = GetNotificationsUseCase(notificationsRepository),
            getNotificationsByIDUseCase = GetNotificationsByIDUseCase(notificationsRepository),
            deleteNotificationUseCase = DeleteNotificationUseCase(notificationsRepository),
            clearNotificationsUseCase = ClearNotificationsUseCase(notificationsRepository),
            insertCardUseCase = InsertCardUseCase(cardRepository),
            getCardsUseCase = GetCardsUseCase(cardRepository),
            getCardByIdUseCase = GetCardByIdUseCase(cardRepository),
            deleteCardUseCase = DeleteCardUseCase(cardRepository),
            updateActiveStatusUseCase = UpdateActiveStatusUseCase(cardRepository),
            getActiveCardsUseCase = GetActiveCardsUseCase(cardRepository),
        )
}