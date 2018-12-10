package com.shrpereira.tweetsense.data.remote.sentiment

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.github.leonardoxh.livedatacalladapter.Resource
import com.shrpereira.tweetsense.data.model.DataResource
import com.shrpereira.tweetsense.data.remote.RetrofitBuilder
import retrofit2.http.Body
import retrofit2.http.POST

interface GoogleDataSource {

	fun postSentiment(content: String): LiveData<DataResource<SentimentResponse>>
}

interface GoogleAPI {
	@POST("/v1/documents:analyzeSentiment")
	fun postSentiment(@Body documentRequest: SentimentRequest)
			: LiveData<Resource<SentimentResponse>>
}

class GoogleDataSourceImpl(
	private val retrofitBuilder: RetrofitBuilder
) : GoogleDataSource {

	override fun postSentiment(content: String): LiveData<DataResource<SentimentResponse>> {
		val sentimentLiveDataResource = retrofitBuilder
			.build(GoogleAPI::class.java)
			.postSentiment(
				SentimentRequest(
					DocumentRequest(
						content
					)
				)
			)

		return Transformations.map(sentimentLiveDataResource) { DataResource.getFromResource(it) }
	}
}