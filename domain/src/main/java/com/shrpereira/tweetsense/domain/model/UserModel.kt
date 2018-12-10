package com.shrpereira.tweetsense.domain.model

data class UserModel(
	val id: Long,
	val name: String?,
	val screenName: String?,
	val location: String?,
	val url: String?,
	val description: String?,
	val photoUrl: String?
)