package bloder.com.presentation.search

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import bloder.com.domain.api_response.auth.AuthError
import bloder.com.domain.api_response.search.SearchError
import bloder.com.domain.interactor.TwitterInteractor
import bloder.com.presentation.AppViewModel

class TwitterViewModel(private val interactor: TwitterInteractor) : AppViewModel<TwitterState>() {

    private val defaultErrorMessages = "An unknown problem has occurred"

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        dispatch(TwitterState.Setup)
    }

    fun getTwitterAuthToken(auth: String) {
        interactor.getTwitterAuthToken(auth).subscribe {
            onSuccess { auth ->
                dispatch(TwitterState.TwitterAuthTokenFetched(auth.accessToken))
            }
            onError { error ->
                when (error) {
                    is AuthError -> dispatch(TwitterState.TwitterAuthTokenFetchFailed(error.errorMessage))
                    else -> dispatch(TwitterState.TwitterAuthTokenFetchFailed(defaultErrorMessages))
                }
            }
        }
    }

    fun fetchTweetsFrom(name: String, auth: String) {
        interactor.searchTweetsFrom(auth, name).subscribe {
            onSuccess { tweets ->
                dispatch(TwitterState.TweetsFetched(tweets))
            }
            onError { error ->
                when (error) {
                    is SearchError -> dispatch(TwitterState.TweetsFetchFailed(error.errorMessage))
                    else -> dispatch(TwitterState.TweetsFetchFailed(defaultErrorMessages))
                }
            }
        }
    }
}