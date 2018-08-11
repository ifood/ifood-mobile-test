package bloder.com.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import bloder.com.domain.api_response.auth.AUTH_ERROR
import bloder.com.domain.api_response.auth.AuthError
import bloder.com.domain.api_response.search.SEARCH_ERROR
import bloder.com.domain.api_response.search.SearchError
import bloder.com.domain.interactor.TwitterInteractor
import bloder.com.domain.repository.RepositoryFactory
import bloder.com.domain.repository.resources.AuthRepository
import bloder.com.domain.repository.resources.SearchRepository
import bloder.com.presentation.search.TwitterState
import bloder.com.presentation.search.TwitterViewModel
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
class TwitterViewModelTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val interactor = mock<TwitterInteractor>()
    private val repository = mock<RepositoryFactory>()
    private val viewModel = TwitterViewModel(interactor)

    @Before fun setup() {
        interactor.test()
    }

    @Test fun completeGetTwitterAuth() {
        viewModel.getTwitterAuthToken("")
        assertTrue(viewModel.state().value is TwitterState.TwitterAuthTokenFetched)
    }

    @Test fun shouldGetErrorOnAuthGetting() {
        forceToGetAuthErrorState(AuthError(AUTH_ERROR.UNKNOWN, ""))
        viewModel.getTwitterAuthToken("")
        assertTrue(viewModel.state().value is TwitterState.TwitterAuthTokenFetchFailed)
    }

    @Test fun completeFetchTweets() {
        viewModel.fetchTweetsFrom("", "")
        assertTrue(viewModel.state().value is TwitterState.TweetsFetched)
    }

    @Test fun shouldGetErrorOnFetchTweets() {
        forceToFetchTweetErrorState(SearchError(SEARCH_ERROR.UNKNOWN, ""))
        viewModel.fetchTweetsFrom("", "")
        assertTrue(viewModel.state().value is TwitterState.TweetsFetchFailed)
    }

    private fun forceToFetchTweetErrorState(error: Throwable) {
        val searchRepository = mock<SearchRepository>()
        interactor.testWith(repository)
        whenever(repository.forSearch()).thenReturn(searchRepository)
        whenever(searchRepository.searchTweets("", "")).thenReturn(Single.create {
            it.onError(error)
        })
    }

    private fun forceToGetAuthErrorState(error: Throwable) {
        val authRepository = mock<AuthRepository>()
        interactor.testWith(repository)
        whenever(repository.forAuth()).thenReturn(authRepository)
        whenever(authRepository.getTwitterAuthToken("")).thenReturn(Single.create {
            it.onError(error)
        })
    }
}