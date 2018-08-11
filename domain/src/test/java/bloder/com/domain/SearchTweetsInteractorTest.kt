package bloder.com.domain

import android.arch.core.executor.testing.InstantTaskExecutorRule
import bloder.com.domain.api_response.search.SEARCH_ERROR
import bloder.com.domain.api_response.search.SearchError
import bloder.com.domain.interactor.SearchTweetsInteractor
import bloder.com.domain.models.Status
import bloder.com.domain.repository.RepositoryFactory
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
class SearchTweetsInteractorTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val interactor = mock<SearchTweetsInteractor>()

    @Before fun setup() {
        interactor.test()
    }

    @Test fun completeFetching() {
        val test = interactor.searchTweetsFrom("", "Bloder").subscribe {}.test()
        test.assertComplete()
    }

    @Test fun returnDataFilled() {
        val test = interactor.searchTweetsFrom("", "Bloder").subscribe {}.test()
        test.assertValue { status ->
            status.isNotEmpty() && status.first().tweet.toLowerCase() == "test!"
        }
    }

    @Test fun returnMappedError() {
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
}