package com.andre.test.features

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andre.test.R
import com.andre.test.core.network.NetworkResponse
import com.andre.test.features.SentimentState.*
import com.andre.test.features.UiState.*
import com.google.api.services.language.v1.model.Sentiment
import com.twitter.sdk.android.core.models.Search
import com.twitter.sdk.android.core.models.Tweet
import javax.inject.Inject

class MainViewModel
@Inject constructor(
    private val getTweets: GetTweets,
    private val analyzeSentiment: AnalyzeSentiment
) : ViewModel() {

    val uiState = MutableLiveData<UiState>()
    val tweetData = MutableLiveData<List<Tweet>>()
    val analyzeData = MutableLiveData<SentimentState>()

    init {
        uiState.value = Initial
    }

    fun queryTweet(query: String?) {
        query?.let {
            uiState.value = Loading
            getTweets.execute(GetTweets.Params(it), ::handleTwitterResponse)
        }
    }

    fun analyzeSentiment(twitterContent: String) {
        uiState.value = Loading
        analyzeSentiment.execute(AnalyzeSentiment.Params(twitterContent), ::handleAnalyzeResponse)
    }

    private fun handleAnalyzeResponse(networkResponse: NetworkResponse) = when (networkResponse) {
        is NetworkResponse.Success<*> -> {
            uiState.value = Success
            val sentiment = networkResponse.response as Sentiment

            val sentimentState =
                when {
                    sentiment.score > 0.25 -> HappyTweet
                    sentiment.score > -0.75 -> NeutralTweet
                    else -> SadTweet
                }
            analyzeData.value = sentimentState
        }
        is NetworkResponse.FetchFailure -> uiState.value = Error(R.string.fetch_error_text)
        is NetworkResponse.NetworkFailure -> uiState.value = Error(R.string.network_error_text)
    }

    private fun handleTwitterResponse(networkResponse: NetworkResponse) {
        when (networkResponse) {
            is NetworkResponse.Success<*> -> {
                val tweets = (networkResponse.response as Search).tweets
                if (tweets.size > 0) {
                    uiState.value = Success
                    tweetData.value = tweets
                } else {
                    uiState.value = Empty
                }
            }
            is NetworkResponse.FetchFailure -> uiState.value = Error(R.string.fetch_error_text)
            is NetworkResponse.NetworkFailure -> uiState.value = Error(R.string.network_error_text)
        }
    }
}