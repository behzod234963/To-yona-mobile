package com.mr.anonym.toyonamobile.di.module

import com.mr.anonym.domain.repository.local.LocalFriendsRepository
import com.mr.anonym.domain.repository.local.LocalPartyRepository
import com.mr.anonym.domain.repository.local.NotificationsRepository
import com.mr.anonym.domain.repository.remote.CardRepository
import com.mr.anonym.domain.repository.remote.FriendsRepository
import com.mr.anonym.domain.repository.remote.PartyRepository
import com.mr.anonym.domain.repository.remote.UserRepository
import com.mr.anonym.domain.useCases.local.notificationUseCase.ClearNotificationsUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.DeleteNotificationUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.GetNotificationsByIDUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.GetNotificationsUseCase
import com.mr.anonym.domain.useCases.local.notificationUseCase.InsertNotificationUseCase
import com.mr.anonym.domain.useCases.local.LocalUseCases
import com.mr.anonym.domain.useCases.local.localFriendsUseCase.DeleteLocalFriendUseCase
import com.mr.anonym.domain.useCases.local.localFriendsUseCase.GetLocalFriendsUseCase
import com.mr.anonym.domain.useCases.local.localFriendsUseCase.InsertLocalFriendUseCase
import com.mr.anonym.domain.useCases.local.localPartyUseCase.ClearAllLocalPartyUseCase
import com.mr.anonym.domain.useCases.local.localPartyUseCase.DeleteLocalPartyUseCase
import com.mr.anonym.domain.useCases.local.localPartyUseCase.GetAllLocalPartyUseCase
import com.mr.anonym.domain.useCases.local.localPartyUseCase.InsertAllLocalPartyUseCase
import com.mr.anonym.domain.useCases.local.localPartyUseCase.InsertLocalPartyUseCase
import com.mr.anonym.domain.useCases.remote.DecodeTokenUseCase
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
import com.mr.anonym.domain.useCases.remote.friends.AddFriendUseCase
import com.mr.anonym.domain.useCases.remote.friends.DeleteFriendUseCase
import com.mr.anonym.domain.useCases.remote.friends.GetAllMyFriendUseCase
import com.mr.anonym.domain.useCases.remote.party.AddPartyUseCase
import com.mr.anonym.domain.useCases.remote.party.DeletePartyRemoteUseCase
import com.mr.anonym.domain.useCases.remote.party.GetAllPartyUseCase
import com.mr.anonym.domain.useCases.remote.party.GetPartyByIdUseCase
import com.mr.anonym.domain.useCases.remote.party.GetUserActiveParties
import com.mr.anonym.domain.useCases.remote.party.GetUserPartiesUseCase
import com.mr.anonym.domain.useCases.remote.party.UpdatePartyUseCase
import com.mr.anonym.domain.useCases.remote.user.DeleteUserUseCase
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
        localPartyRepository: LocalPartyRepository,
        localFriendRepository: LocalFriendsRepository
    ): LocalUseCases =
        LocalUseCases(
            insertNotificationUseCase = InsertNotificationUseCase(notificationsRepository),
            getNotificationsUseCase = GetNotificationsUseCase(notificationsRepository),
            getNotificationsByIDUseCase = GetNotificationsByIDUseCase(notificationsRepository),
            deleteNotificationUseCase = DeleteNotificationUseCase(notificationsRepository),
            clearNotificationsUseCase = ClearNotificationsUseCase(notificationsRepository),
            insertAllParty = InsertAllLocalPartyUseCase(localPartyRepository),
            getAllLocalParty = GetAllLocalPartyUseCase(localPartyRepository),
            clearAllParty = ClearAllLocalPartyUseCase(localPartyRepository),
            insertLocalParty = InsertLocalPartyUseCase(localPartyRepository),
            deleteLocalParty = DeleteLocalPartyUseCase(localPartyRepository),
            insertLocalFriend = InsertLocalFriendUseCase(localFriendRepository),
            getLocalFriends = GetLocalFriendsUseCase(localFriendRepository),
            deleteLocalFriend = DeleteLocalFriendUseCase(localFriendRepository)
        )

    @Provides
    @Singleton
    fun provideRemoteUseCases(
        userRepository: UserRepository,
        partyRepository: PartyRepository,
        cardRepository: CardRepository,
        friendsRepository: FriendsRepository
    ): RemoteUseCases =
        RemoteUseCases(
            loginUserUseCase = LoginUserUseCase(userRepository),
            registerUserUseCase = RegisterUserUseCase(userRepository),
            getUserUseCase = GetUserByIdUseCase(userRepository),
            updateUserUseCase = UpdateUserUseCase(userRepository),
            searchUserUseCase = SearchUserUseCase(userRepository),
            addPartyUseCase = AddPartyUseCase(partyRepository),
            updatePartyUseCase = UpdatePartyUseCase(partyRepository),
            getAllPartyUseCase = GetAllPartyUseCase(partyRepository),
            getPartyByIdUseCase = GetPartyByIdUseCase(partyRepository),
            deletePartyUseCase = DeletePartyRemoteUseCase(partyRepository),
            addCardUseCase = AddCardUseCase(cardRepository),
            getAllCardUseCase = GetAllCardUseCase(cardRepository),
            getCardByIdUseCase = GetCardByIdUseCase(cardRepository),
            updateCardUseCase = UpdateCardUseCase(cardRepository),
            deleteCardUseCase = DeleteCardUseCase(cardRepository),
            getUserCardsUseCase = GetUserCardsUseCase(cardRepository),
            getUserPartiesUseCase = GetUserPartiesUseCase(partyRepository),
            decodeTokenUseCase = DecodeTokenUseCase(userRepository),
            deleteUserUseCase = DeleteUserUseCase(userRepository),
            getUserActiveParties = GetUserActiveParties(partyRepository),
            addFriendUseCase = AddFriendUseCase(friendsRepository),
            getAllMyFriendUseCase = GetAllMyFriendUseCase(friendsRepository),
            deleteFriendUseCase = DeleteFriendUseCase(friendsRepository),
        )
}