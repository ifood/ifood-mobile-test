package com.shrpereira.tweetsense.data.model

import com.google.gson.annotations.SerializedName

class TwitterTokenResponse(
	@SerializedName("access_token") val accessToken: String?,
	@SerializedName("token_type") val tokenType: String?
) {

	val authorization: String
		get() = "$tokenType $accessToken"

}