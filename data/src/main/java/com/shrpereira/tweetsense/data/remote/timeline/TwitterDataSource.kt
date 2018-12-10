package com.shrpereira.tweetsense.data.remote.timeline

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.github.leonardoxh.livedatacalladapter.Resource
import com.shrpereira.tweetsense.data.model.DataResource
import com.shrpereira.tweetsense.data.remote.RetrofitBuilder
import retrofit2.http.GET
import retrofit2.http.Query

interface TwitterDataSource {

	fun getTimeLine(screenName: String): LiveData<DataResource<List<TweetResponseModel>>>
}

interface TwitterAPI {

	@GET("1.1/statuses/user_timeline.json")
	fun getTimeLine(@Query("screen_name") screenName: String): LiveData<Resource<List<TweetResponseModel>>>
}

class TwitterDataSourceImpl(
	private val retrofitBuilder: RetrofitBuilder
) : TwitterDataSource {

	override fun getTimeLine(screenName: String): LiveData<DataResource<List<TweetResponseModel>>> {
		val timeLineLiveDataResource = retrofitBuilder
			.build(TwitterAPI::class.java)
			.getTimeLine(screenName)

		return Transformations.map(timeLineLiveDataResource) { DataResource.getFromResource(it) }
	}
}