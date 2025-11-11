package com.mr.anonym.domain.useCases.local

import androidx.annotation.Keep
import com.mr.anonym.domain.useCases.local.localCardUtilUseCase.DeleteCardUtilUseCase
import com.mr.anonym.domain.useCases.local.localCardUtilUseCase.GetCardIndexByIdUseCase
import com.mr.anonym.domain.useCases.local.localCardUtilUseCase.InsertCardUtilUseCase
import com.mr.anonym.domain.useCases.local.localFriendsUseCase.DeleteLocalFriendUseCase
import com.mr.anonym.domain.useCases.local.localFriendsUseCase.GetLocalFriendsUseCase
import com.mr.anonym.domain.useCases.local.localFriendsUseCase.InsertLocalFriendUseCase
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
//    Notification use cases
    val insertNotificationUseCase: InsertNotificationUseCase,
    val getNotificationsUseCase: GetNotificationsUseCase,
    val getNotificationsByIDUseCase: GetNotificationsByIDUseCase,
    val deleteNotificationUseCase: DeleteNotificationUseCase,
    val clearNotificationsUseCase: ClearNotificationsUseCase,
//    Local party use cases
    val insertLocalParty: InsertLocalPartyUseCase,
    val insertAllParty: InsertAllLocalPartyUseCase,
    val getAllLocalParty: GetAllLocalPartyUseCase,
    val clearAllParty: ClearAllLocalPartyUseCase,
    val deleteLocalParty: DeleteLocalPartyUseCase,
//    Local Friend use cases
    val insertLocalFriend: InsertLocalFriendUseCase,
    val getLocalFriends: GetLocalFriendsUseCase,
    val deleteLocalFriend: DeleteLocalFriendUseCase,
//    Local card utils use cases
    val insertCardUtilUseCase: InsertCardUtilUseCase,
    val getCardIndexByIdUseCase: GetCardIndexByIdUseCase,
    val deleteCardUtilUseCase: DeleteCardUtilUseCase
)
