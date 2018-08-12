package bloder.com.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import bloder.com.domain.api_response.search.SEARCH_ERROR
import bloder.com.domain.api_response.search.SearchError
import bloder.com.domain.interactor.TwitterInteractor
import bloder.com.domain.repository.RepositoryFactory
import bloder.com.domain.repository.resources.SearchRepository
import bloder.com.presentation.twitter.search.SearchTweetsState
import bloder.com.presentation.twitter.search.SearchViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SearchTweetsViewModelTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val interactor = mock<TwitterInteractor>()
    private val repository = mock<RepositoryFactory>()
    private val viewModel = SearchViewModel(interactor)

    @Before fun setup() {
        interactor.test()
    }

    @Test fun completeFetchTweets() {
        viewModel.fetchTweetsFrom("", "")
        assertTrue(viewModel.state().value is SearchTweetsState.TweetsFetched)
    }

    @Test fun shouldGetErrorOnFetchTweets() {
        forceToFetchTweetErrorState(SearchError(SEARCH_ERROR.UNKNOWN, ""))
        viewModel.fetchTweetsFrom("", "")
        assertTrue(viewModel.state().value is SearchTweetsState.TweetsFetchFailed)
    }

    private fun forceToFetchTweetErrorState(error: Throwable) {
        val searchRepository = mock<SearchRepository>()
        interactor.testWith(repository)
        whenever(repository.forSearch()).thenReturn(searchRepository)
        whenever(searchRepository.searchTweets("", "")).thenReturn(Single.create {
            it.onError(error)
        })
    }
}