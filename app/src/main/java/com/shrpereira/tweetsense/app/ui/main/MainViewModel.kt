package com.shrpereira.tweetsense.app.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.shrpereira.tweetsense.domain.model.TweetModel
import com.shrpereira.tweetsense.domain.sentiment.GoogleSentimentUseCase
import com.shrpereira.tweetsense.domain.timeline.TwitterTimelineUseCase

class MainViewModel(
	private val twitterUseCase: TwitterTimelineUseCase,
	private val sentimentUseCase: GoogleSentimentUseCase
) : ViewModel() {

	private val screenNameLiveData: MutableLiveData<String> = MutableLiveData()
	private val selectedTweetLiveData: MutableLiveData<String> = MutableLiveData()

	val tweetsLiveData: LiveData<TweetState> by lazy {
		val tweetsResourceLiveData = Transformations.switchMap(screenNameLiveData) { screenName ->
			twitterUseCase.execute(screenName)
		}

		Transformations.switchMap(tweetsResourceLiveData) { resource ->
			val liveData = MutableLiveData<TweetState>()
			resource.error?.let { throwable ->
				if (throwable is IllegalStateException) {
					liveData.value = TweetState.EmptyNickname
				} else {
					resource.error?.message?.let { liveData.value = TweetState.Error(it) }
				}
			}

			resource.responseData?.let { liveData.value = TweetState.Success(it) }
			liveData
		}
	}

	val sentimentLiveData: LiveData<SentimentState> by lazy {
		val sentimentResourceLiveData =
			Transformations.switchMap(selectedTweetLiveData) { tweetText ->
				sentimentUseCase.execute(tweetText)
			}

		Transformations.switchMap(sentimentResourceLiveData) { resource ->
			val liveData = MutableLiveData<SentimentState>()
			resource.error?.message?.let { liveData.value = SentimentState.Error(it) }
			resource.responseData?.let {
				when {
					it.score == 0.0 -> {
						liveData.value = SentimentState.Neutral
					}
					it.score > 0.0 -> {
						liveData.value = SentimentState.Positive
					}
					it.score < 0.0 -> {
						liveData.value = SentimentState.Negative
					}
				}
			}
			liveData
		}
	}

	fun getTweets(screenName: String) {
		screenNameLiveData.value = screenName
	}

	fun getTweetSentiment(tweetText: String) {
		selectedTweetLiveData.value = tweetText
	}

	sealed class TweetState {
		data class Success(val tweets: List<TweetModel>) : TweetState()
		data class Error(val message: String) : TweetState()
		object EmptyNickname : TweetState()
	}

	sealed class SentimentState {
		object Positive : SentimentState()
		object Negative : SentimentState()
		object Neutral : SentimentState()
		data class Error(val message: String) : SentimentState()
	}
}