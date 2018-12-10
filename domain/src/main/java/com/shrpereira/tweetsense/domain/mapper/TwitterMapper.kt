package com.shrpereira.tweetsense.domain.mapper

import com.shrpereira.tweetsense.data.remote.timeline.TweetResponseModel
import com.shrpereira.tweetsense.data.remote.timeline.UserResponseModel
import com.shrpereira.tweetsense.domain.model.TweetModel
import com.shrpereira.tweetsense.domain.model.UserModel

class TwitterMapper {

	fun map(response: List<TweetResponseModel>?): List<TweetModel>? {
		response?.let { checkedResponse ->
			return checkedResponse
				.map { TweetModel(it.createdAt, it.idStr, it.text, mapUser(it.user)) }
				.toList()
		}
		return null
	}

	private fun mapUser(userResponse: UserResponseModel): UserModel {
		return UserModel(
			userResponse.id,
			userResponse.name,
			userResponse.screenName,
			userResponse.location,
			userResponse.url,
			userResponse.description,
			userResponse.profileImageUrl
		)
	}
}