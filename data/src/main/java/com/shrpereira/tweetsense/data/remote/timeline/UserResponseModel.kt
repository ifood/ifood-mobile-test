package com.shrpereira.tweetsense.data.remote.timeline

import com.google.gson.annotations.SerializedName

data class UserResponseModel(
	@SerializedName("id") val id: Long,
	@SerializedName("name") val name: String,
	@SerializedName("screen_name") val screenName: String,
	@SerializedName("location") val location: String,
	@SerializedName("url") val url: String,
	@SerializedName("description") val description: String,
	@SerializedName("profile_image_url") val profileImageUrl: String?
)