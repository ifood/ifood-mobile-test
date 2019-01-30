package com.andre.test.features

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andre.test.R
import com.andre.test.core.network.NetworkResponse
import com.andre.test.core.platform.NetworkHandler
import com.google.api.services.language.v1.CloudNaturalLanguage
import com.google.api.services.language.v1.model.Sentiment
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.models.Search
import com.twitter.sdk.android.core.models.Tweet

class MainViewModel : ViewModel() {

    val uiState = MutableLiveData<UiState>()
    val tweetData = MutableLiveData<List<Tweet>>()
    val analyzeData = MutableLiveData<Sentiment>()

    init {
        uiState.value = UiState.Initial
    }

    fun queryTweet(query: String?) {
        query?.let {
            val repository = TwitterRepository(
                TwitterCore.getInstance().apiClient.searchService,
                NetworkHandler(TestApplication.context)
            )
            val getTweets = GetTweets(repository)

            uiState.value = UiState.Loading
            getTweets.execute(GetTweets.Params(it), ::handleTwitterResponse)
        }
    }

    fun analyzeSentiment(naturalLanguage: CloudNaturalLanguage, twitterContent: String) {
        val analyzeSentiment = AnalyzeSentiment(naturalLanguage)
        analyzeSentiment.execute(AnalyzeSentiment.Params(twitterContent), ::handleAnalyzeResponse)
    }

    private fun handleAnalyzeResponse(networkResponse: NetworkResponse) {
        when (networkResponse) {
            is NetworkResponse.Success<*> -> {
                analyzeData.value = networkResponse.response as Sentiment
            }
            is NetworkResponse.FetchFailure -> uiState.value = UiState.Error(R.string.fetch_error_text)
            is NetworkResponse.NetworkFailure -> uiState.value = UiState.Error(R.string.network_error_text)
        }
    }

    private fun handleTwitterResponse(networkResponse: NetworkResponse) {
        when (networkResponse) {
            is NetworkResponse.Success<*> -> {
                val tweets = (networkResponse.response as Search).tweets
                if (tweets.size > 0) {
                    uiState.value = UiState.Success
                    tweetData.value = tweets
                } else {
                    uiState.value = UiState.Empty
                }
            }
            is NetworkResponse.FetchFailure -> uiState.value = UiState.Error(R.string.fetch_error_text)
            is NetworkResponse.NetworkFailure -> uiState.value = UiState.Error(R.string.network_error_text)
        }
    }
}