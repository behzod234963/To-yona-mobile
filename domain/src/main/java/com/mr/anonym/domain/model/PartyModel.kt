package com.mr.anonym.domain.model

data class PartyModel(
	val allparty: Int? = null,
	val allpages: Int? = null,
	val partys: List<PartysItem>? = null,
	val limit: Int? = null,
	val currentpage: Int? = null
)

data class PartysItem(
	val id: Int? = null,
	val userId: Int? = null,
	val name: String? = null,
	val type: String? = null,
	val address: String? = null,
	val cardNumber: String? = null,
	val startTime: String? = null,
	val endTime: String? = null,
	val status: Boolean? = null,
	val createdAt: String? = null,
	val user: UserModelItem? = null
)

