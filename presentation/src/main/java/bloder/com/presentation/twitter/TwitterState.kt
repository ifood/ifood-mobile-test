package bloder.com.presentation.twitter

import bloder.com.domain.models.search.Status

sealed class TwitterState {

    class TweetsFetched(val tweets: List<Status>) : TwitterState()
    class TweetsFetchFailed(val errorMessage: String) : TwitterState()
    class TwitterAuthTokenFetched(val authToken: String) : TwitterState()
    class TwitterAuthTokenFetchFailed(errorMessage: String) : TwitterState()
    object Setup : TwitterState()
}