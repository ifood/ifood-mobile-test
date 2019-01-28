package com.andre.test.features

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andre.test.core.NetworkResponse
import com.andre.test.core.platform.NetworkHandler
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.models.Search
import com.twitter.sdk.android.core.models.Tweet

class MainViewModel : ViewModel() {

    val uiState = MutableLiveData<UiState>()
    val tweetData = MutableLiveData<List<Tweet>>()

    init {
        uiState.value = UiState.Empty
    }

    fun queryTweet(query: String?) {
        query?.let {
            val repository = TwitterRepository(
                TwitterCore.getInstance().apiClient.searchService,
                NetworkHandler(TestApplication.context)
            )
            val getTweets = GetTweets(repository)

            uiState.value = UiState.Loading
            getTweets.execute(GetTweets.Params(it), ::handleResponse)
        }
    }

    private fun handleResponse(networkResponse: NetworkResponse) {
        when (networkResponse) {
            is NetworkResponse.Success<*> -> {
                uiState.value = UiState.Sucess
                tweetData.value = (networkResponse.response as Search).tweets
            }
            is NetworkResponse.FetchFailure -> uiState.value = UiState.Error
        }
    }
}