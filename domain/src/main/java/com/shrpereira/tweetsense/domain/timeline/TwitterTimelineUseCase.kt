package com.shrpereira.tweetsense.domain.timeline

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.shrpereira.tweetsense.data.model.DataResource
import com.shrpereira.tweetsense.data.remote.timeline.TweetResponseModel
import com.shrpereira.tweetsense.data.remote.timeline.TwitterDataSource
import com.shrpereira.tweetsense.domain.mapper.TwitterMapper
import com.shrpereira.tweetsense.domain.model.TweetModel

interface TwitterTimelineUseCase {

	fun execute(screenName: String): LiveData<DataResource<List<TweetModel>>>
}

class TwitterTimelineUseCaseImpl(
	private val twitterDataSource: TwitterDataSource,
	private val mapper: TwitterMapper
) : TwitterTimelineUseCase {

	override fun execute(screenName: String): LiveData<DataResource<List<TweetModel>>> {
		if (screenName.isEmpty()) {
			return MutableLiveData<DataResource<List<TweetModel>>>().apply {
				value = DataResource.newInstance(null, IllegalStateException())
			}
		}
		return Transformations.map(twitterDataSource.getTimeLine(screenName), ::mapTimeline)
	}

	private fun mapTimeline(response: DataResource<List<TweetResponseModel>>) =
		response.switchTo(mapper.map(response.responseData), response.error)
}