package com.shrpereira.tweetsense.data.remote.timeline

import com.google.gson.annotations.SerializedName

data class TweetResponseModel(
	@SerializedName("created_at") val createdAt: String,
	@SerializedName("id_str") val idStr: String,
	@SerializedName("text") val text: String,
	@SerializedName("user") val user: UserResponseModel
)