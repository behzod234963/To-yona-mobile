package com.mr.anonym.domain.model

import androidx.annotation.Keep

data class UserModel(
	val userModel: List<UserModelItem> = emptyList()
)

@Keep data class UserModelItem(
	val id: Int = -1,
	val username: String = "",
	val surname: String = "",
	val sex: Int = 0,
	val phonenumber: String = "",
	val password: String = "",
	val cardlist: List<CardModel> = emptyList(),
	val partylist: List<PartysItem> = emptyList(),
	val createdAt: String = ""
)

