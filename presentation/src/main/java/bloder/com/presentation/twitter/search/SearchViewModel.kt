package bloder.com.presentation.twitter.search

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import bloder.com.domain.api_response.search.SearchError
import bloder.com.domain.interactor.TwitterInteractor
import bloder.com.presentation.AppViewModel

class SearchViewModel(private val interactor: TwitterInteractor) : AppViewModel<SearchTweetsState>() {

    private val defaultErrorMessages = "An unknown problem has occurred"

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE) fun onCreate() {
        dispatch(SearchTweetsState.Setup)
    }

    fun fetchTweetsFrom(name: String, authToken: String) {
        val auth = "Bearer $authToken"
        interactor.searchTweetsFrom(auth, name).subscribe {
            onSuccess { tweets ->
                dispatch(SearchTweetsState.TweetsFetched(tweets))
            }
            onError { error ->
                when (error) {
                    is SearchError -> dispatch(SearchTweetsState.TweetsFetchFailed(error.errorMessage))
                    else -> dispatch(SearchTweetsState.TweetsFetchFailed(defaultErrorMessages))
                }
            }
        }
    }
}