package bloder.com.presentation.twitter.search

import bloder.com.domain.models.search.Status

sealed class SearchTweetsState {

    class TweetsFetched(val tweets: List<Status>) : SearchTweetsState()
    class TweetsFetchFailed(val errorMessage: String) : SearchTweetsState()
    object Setup : SearchTweetsState()
}