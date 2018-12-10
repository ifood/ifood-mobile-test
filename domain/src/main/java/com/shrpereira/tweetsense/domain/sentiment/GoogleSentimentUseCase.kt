package com.shrpereira.tweetsense.domain.sentiment

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.shrpereira.tweetsense.data.model.DataResource
import com.shrpereira.tweetsense.data.remote.sentiment.GoogleDataSource
import com.shrpereira.tweetsense.data.remote.sentiment.SentimentResponse
import com.shrpereira.tweetsense.domain.mapper.SentimentMapper
import com.shrpereira.tweetsense.domain.model.SentimentModel

interface GoogleSentimentUseCase {

	fun execute(content: String): LiveData<DataResource<SentimentModel>>?
}

class GoogleSentimentUseCaseImpl(
	private val googleDataSource: GoogleDataSource,
	private val mapper: SentimentMapper
) : GoogleSentimentUseCase {

	override fun execute(content: String): LiveData<DataResource<SentimentModel>>? {
		return Transformations.map(googleDataSource.postSentiment(content), ::mapGoogleSentiment)
	}

	private fun mapGoogleSentiment(response: DataResource<SentimentResponse>) =
		response.switchTo(mapper.map(response.responseData), response.error)
}