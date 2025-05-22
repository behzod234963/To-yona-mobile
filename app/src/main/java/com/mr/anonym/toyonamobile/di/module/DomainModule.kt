package com.mr.anonym.toyonamobile.di.module

import com.mr.anonym.domain.repository.local.NotificationsRepository
import com.mr.anonym.domain.repository.remote.CardRepository
import com.mr.anonym.domain.repository.remote.PartyRepository
import com.mr.anonym.domain.repository.remote.UserRepository
import com.mr.anonym.domain.useCases.local.notificationUseCase.ClearNotificationsUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.DeleteNotificationUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.GetNotificationsByIDUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.GetNotificationsUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.InsertNotificationUseCase
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.domain.useCases.remote.user.GetUserByIdUseCase
import com.mr.anonym.domain.useCases.remote.user.LoginUserUseCase
import com.mr.anonym.domain.useCases.remote.user.RegisterUserUseCase
import com.mr.anonym.domain.useCases.remote.RemoteUseCases
import com.mr.anonym.domain.useCases.remote.card.AddCardUseCase
import com.mr.anonym.domain.useCases.remote.card.DeleteCardUseCase
import com.mr.anonym.domain.useCases.remote.card.GetAllCardUseCase
import com.mr.anonym.domain.useCases.remote.card.GetCardByIdUseCase
import com.mr.anonym.domain.useCases.remote.card.GetUserCardsUseCase
import com.mr.anonym.domain.useCases.remote.card.UpdateCardUseCase
import com.mr.anonym.domain.useCases.remote.party.AddPartyUseCase
import com.mr.anonym.domain.useCases.remote.party.DeletePartyRemoteUseCase
import com.mr.anonym.domain.useCases.remote.party.GetAllPartyUseCase
import com.mr.anonym.domain.useCases.remote.party.GetPartyByIdUseCase
import com.mr.anonym.domain.useCases.remote.party.GetUserPartiesUseCase
import com.mr.anonym.domain.useCases.remote.party.UpdatePartyUseCase
import com.mr.anonym.domain.useCases.remote.user.SearchUserUseCase
import com.mr.anonym.domain.useCases.remote.user.UpdateUserUseCase
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
    ): LocalUseCases =
        LocalUseCases(
            insertNotificationUseCase = InsertNotificationUseCase(notificationsRepository),
            getNotificationsUseCase = GetNotificationsUseCase(notificationsRepository),
            getNotificationsByIDUseCase = GetNotificationsByIDUseCase(notificationsRepository),
            deleteNotificationUseCase = DeleteNotificationUseCase(notificationsRepository),
            clearNotificationsUseCase = ClearNotificationsUseCase(notificationsRepository),
        )

    @Provides
    @Singleton
    fun provideRemoteUseCases(userRepository: UserRepository,partyRepository: PartyRepository,cardRepository: CardRepository): RemoteUseCases =
        RemoteUseCases(
            loginUserUseCase = LoginUserUseCase(userRepository),
            registerUserUseCase = RegisterUserUseCase(userRepository),
            getUserByIdUseCase = GetUserByIdUseCase(userRepository),
            updateUserUseCase = UpdateUserUseCase(userRepository),
            getAllPartyUseCase = GetAllPartyUseCase(partyRepository),
            searchUserUseCase = SearchUserUseCase(userRepository),
            addPartyUseCase = AddPartyUseCase(partyRepository),
            deletePartyUseCase = DeletePartyRemoteUseCase(partyRepository),
            getPartyByIdUseCase = GetPartyByIdUseCase(partyRepository),
            addCardUseCase = AddCardUseCase(cardRepository),
            getAllCardUseCase = GetAllCardUseCase(cardRepository),
            getCardByIdUseCase = GetCardByIdUseCase(cardRepository),
            updateCardUseCase = UpdateCardUseCase(cardRepository),
            deleteCardUseCase = DeleteCardUseCase(cardRepository),
            getUserCardsUseCase = GetUserCardsUseCase(cardRepository),
            updatePartyUseCase = UpdatePartyUseCase(partyRepository),
            getUserPartiesUseCase = GetUserPartiesUseCase(partyRepository)
        )
}