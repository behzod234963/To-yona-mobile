package com.mr.anonym.domain.useCases.local.localFriendsUseCase

import com.mr.anonym.domain.model.LocalFriendsModel
import com.mr.anonym.domain.repository.local.LocalFriendsRepository

class DeleteLocalFriendUseCase(private val repository: LocalFriendsRepository) {
    suspend fun execute(localFriend: LocalFriendsModel){
        repository.deleteLocalFriend(localFriend)
    }
}