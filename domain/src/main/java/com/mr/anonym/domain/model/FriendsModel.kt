package com.mr.anonym.domain.model

data class FriendsModel(
	val addedByMe: List<AddedByMeItem> = emptyList(),
	val addedMe: List<Any?>? = null
)

data class AddedByMeItem(
	val friendId: Int = -1,
	val friend: Friend = Friend(),
	val id: Int = -1,
	val userId: Int = -1
)

data class Friend(
	val createdAt: String = "",
	val password: String = "",
	val surname: String = "",
	val sex: Int = -1,
	val phonenumber: String = "",
	val id: Int = -1,
	val username: String = ""
)

