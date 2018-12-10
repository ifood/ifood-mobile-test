package com.shrpereira.tweetsense.domain.mapper

import com.shrpereira.tweetsense.data.remote.sentiment.SentimentResponse
import com.shrpereira.tweetsense.domain.model.SentimentModel

class SentimentMapper {

	fun map(response: SentimentResponse?): SentimentModel? {
		response?.let {
			return SentimentModel(it.documentSentiment.score ?: 0.0)
		}

		return null
	}
}