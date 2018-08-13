package bloder.com.domain

import android.arch.core.executor.testing.InstantTaskExecutorRule
import bloder.com.domain.interactor.SentimentInteractor
import bloder.com.domain.models.sentiment.SENTIMENT
import bloder.com.domain.models.sentiment.Sentiment
import bloder.com.domain.repository.RepositoryFactory
import bloder.com.domain.repository.resources.SentimentRepository
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
class SentimentInteractorTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val interactor = mock<SentimentInteractor>()

    @Before fun setup() {
        interactor.test()
    }

    @Test fun completeGetTweetSentiment() {
        val test = interactor.getSentimentFrom("test").subscribe {}.test()
        test.assertComplete()
    }

    @Test fun returnFilledSentiment() {
        val test = interactor.getSentimentFrom("test").subscribe {}.test()
        test.assertValue { value ->
            value.getSentiment() == SENTIMENT.SAD
        }
    }

    @Test fun returnErrorWhenGetSentiment() {
        val errorMessage = "A unknown problem has occurred"
        val repository = mock<RepositoryFactory>()
        val searchRepository = mock<SentimentRepository>()
        interactor.testWith(repository)
        whenever(repository.forSentiment()).thenReturn(searchRepository)
        whenever(searchRepository.getSentimentFor(any())).thenReturn(Single.create<Sentiment> {
            it.onError(Throwable(errorMessage))
        })
        val test = interactor.getSentimentFrom("").subscribe {}.test()
        test.assertError { error ->
            error.message == errorMessage
        }
    }
}