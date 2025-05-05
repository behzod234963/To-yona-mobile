package com.mr.anonym.toyonamobile.di.module

import com.mr.anonym.domain.repository.local.CardRepository
import com.mr.anonym.domain.repository.local.MyEventsRepository
import com.mr.anonym.domain.repository.local.NotificationsRepository
import com.mr.anonym.domain.useCases.local.notificationUseCase.ClearNotificationsUseCase
import com.mr.anonym.domain.useCases.local.cardUseCases.DeleteCardUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.DeleteNotificationUseCase
import com.mr.anonym.domain.useCases.local.cardUseCases.GetCardByIdUseCase
import com.mr.anonym.domain.useCases.local.cardUseCases.GetCardsUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.GetNotificationsByIDUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.GetNotificationsUseCase
import com.mr.anonym.domain.useCases.local.cardUseCases.InsertCardUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.InsertNotificationUseCase
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.domain.useCases.local.myEventUseCase.DeleteEventUseCase
import com.mr.anonym.domain.useCases.local.myEventUseCase.GetActiveEventsUseCase
import com.mr.anonym.domain.useCases.local.myEventUseCase.GetEventByIdUseCase
import com.mr.anonym.domain.useCases.local.myEventUseCase.GetEventsUseCase
import com.mr.anonym.domain.useCases.local.myEventUseCase.InsertEventUseCase
import com.mr.anonym.domain.useCases.local.myEventUseCase.UpdateEventStatusUseCase
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
        myEventsRepository: MyEventsRepository
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
            insertEventUseCase = InsertEventUseCase(myEventsRepository),
            updateEventStatusUseCase = UpdateEventStatusUseCase(myEventsRepository),
            deleteEventUseCase = DeleteEventUseCase(myEventsRepository),
            getEventsUseCase = GetEventsUseCase(myEventsRepository),
            getEventByIdUseCase = GetEventByIdUseCase(myEventsRepository),
            getActiveEventsUseCase = GetActiveEventsUseCase(myEventsRepository),
        )
}