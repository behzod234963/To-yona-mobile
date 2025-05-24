package com.mr.anonym.domain.response

data class DecodeTokenResponse(
	val decoded: Decoded? = null
)

data class Decoded(
	val id: Int? = null,
	val exp: Int? = null,
	val iat: Int? = null
)

