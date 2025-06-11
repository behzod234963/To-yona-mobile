package com.mr.anonym.domain.model

import androidx.annotation.Keep

@Keep data class FriendsModel(
	val addedByMe: List<AddedByMeItem> = emptyList(),
	val addedMe: List<Any?>? = null
)

@Keep data class AddedByMeItem(
	val friendId: Int = -1,
	val friend: Friend = Friend(),
	val id: Int = -1,
	val userId: Int = -1
)

@Keep data class Friend(
	val createdAt: String = "",
	val password: String = "",
	val surname: String = "",
	val sex: Int = -1,
	val phonenumber: String = "",
	val id: Int = -1,
	val username: String = ""
)

