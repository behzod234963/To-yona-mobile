package com.mr.anonym.domain.model

data class UserModel(
	val userModel: List<UserModelItem?>? = null
)

data class UserModelItem(
	val createdAt: String? = null,
	val password: String? = null,
	val cardlist: List<Any?>? = null,
	val surname: String? = null,
	val phonenumber: String? = null,
	val id: Int? = null,
	val username: String? = null
)

