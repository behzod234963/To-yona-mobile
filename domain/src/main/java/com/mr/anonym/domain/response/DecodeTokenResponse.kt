package com.mr.anonym.domain.response

import androidx.annotation.Keep

@Keep data class DecodeTokenResponse(
	val decoded: Decoded? = null
)
@Keep data class Decoded(
	val id: Int? = null,
	val exp: Int? = null,
	val iat: Int? = null
)

