package com.shrpereira.tweetsense.domain.model

data class TweetModel(
	val createdAt: String,
	val idStr: String,
	val text: String,
	val user: UserModel
)