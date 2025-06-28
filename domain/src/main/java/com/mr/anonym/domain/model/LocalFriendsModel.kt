package com.mr.anonym.domain.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity("LocalFriendsModel")
data class LocalFriendsModel(
	@PrimaryKey
	val id: Int = -1,
	val friendId: Int = -1,
	val userId: Int = -1,
)
