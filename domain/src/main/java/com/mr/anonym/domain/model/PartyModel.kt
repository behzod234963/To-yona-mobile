package com.mr.anonym.domain.model

data class PartyModel(
	val allparty: Int = 0,
	val allpages: Int = 0,
	val partys: List<PartysItem> = emptyList(),
	val limit: Int? = null,
	val currentpage: Int? = null
)

data class PartysItem(
	val id: Int = -1,
	val userId: Int = -1,
	val userName: String = "",
	val name: String = "",
	val type: String = "",
	val address: String = "",
	val cardNumber: String = "",
	val startTime: String = "",
	val endTime: String = "",
	val status: Boolean = true,
	val createdAt: String = "",
	val user: UserModelItem = UserModelItem()
)

