package bloder.com.domain

import android.arch.core.executor.testing.InstantTaskExecutorRule
import bloder.com.domain.api_response.auth.AUTH_ERROR
import bloder.com.domain.api_response.auth.AuthError
import bloder.com.domain.api_response.search.SEARCH_ERROR
import bloder.com.domain.api_response.search.SearchError
import bloder.com.domain.interactor.TwitterInteractor
import bloder.com.domain.models.auth.TwitterAuth
import bloder.com.domain.models.search.Status
import bloder.com.domain.repository.RepositoryFactory
import bloder.com.domain.repository.resources.AuthRepository
import bloder.com.domain.repository.resources.SearchRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class TwitterInteractorTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val interactor = mock<TwitterInteractor>()

    @Before fun setup() {
        interactor.test()
    }

    @Test fun completeSearchTweets() {
        val test = interactor.searchTweetsFrom("", "Bloder").subscribe {}.test()
        test.assertComplete()
    }

    @Test fun returnFilledTweet() {
        val test = interactor.searchTweetsFrom("", "Bloder").subscribe {}.test()
        test.assertValue { status ->
            status.isNotEmpty() && status.first().tweet.toLowerCase() == "test!"
        }
    }

    @Test fun returnErrorOnTweetsSearch() {
        val errorMessage = "Unknown problem!"
        val repository = mock<RepositoryFactory>()
        val searchRepository = mock<SearchRepository>()
        interactor.testWith(repository)
        whenever(repository.forSearch()).thenReturn(searchRepository)
        whenever(searchRepository.searchTweets(any(), any())).thenReturn(Single.create<List<Status>> {
            it.onError(SearchError(SEARCH_ERROR.UNKNOWN, errorMessage))
        })
        val test = interactor.searchTweetsFrom("", "").subscribe {}.test()
        test.assertError { error ->
            error is SearchError && error.errorMessage == errorMessage
        }
    }

    @Test fun completeAuthTokenCall() {
        val test = interactor.getTwitterAuthToken("").subscribe {}.test()
        test.assertComplete()
    }

    @Test fun returnFilledAuthToken() {
        val test = interactor.getTwitterAuthToken("").subscribe {}.test()
        test.assertValue { auth ->
            auth.accessToken.isNotEmpty()
        }
    }

    @Test fun returnErrorWhenGetAuth() {
        val errorMessage = "Unknown problem!"
        val repository = mock<RepositoryFactory>()
        val authRepository = mock<AuthRepository>()
        interactor.testWith(repository)
        whenever(repository.forAuth()).thenReturn(authRepository)
        whenever(authRepository.getTwitterAuthToken(any())).thenReturn(Single.create<TwitterAuth> {
            it.onError(AuthError(AUTH_ERROR.UNKNOWN, errorMessage))
        })
        val test = interactor.getTwitterAuthToken("").subscribe {}.test()
        test.assertError { error ->
            error is AuthError && error.errorMessage == errorMessage
        }
    }
}