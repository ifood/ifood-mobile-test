package bloder.com.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import bloder.com.domain.api_response.auth.AUTH_ERROR
import bloder.com.domain.api_response.auth.AuthError
import bloder.com.domain.interactor.SentimentInteractor
import bloder.com.domain.repository.RepositoryFactory
import bloder.com.domain.repository.resources.AuthRepository
import bloder.com.domain.repository.resources.SentimentRepository
import bloder.com.presentation.twitter.auth.AuthState
import bloder.com.presentation.twitter.sentiment.SentimentState
import bloder.com.presentation.twitter.sentiment.SentimentViewModel
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import junit.framework.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SentimentViewModelTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val interactor = mock<SentimentInteractor>()
    private val repository = mock<RepositoryFactory>()
    private val viewModel = SentimentViewModel(interactor)

    @Before fun setup() {
        interactor.test()
    }

    @Test fun completeGetTwitterAuth() {
        viewModel.getSentimentFor("")
        Assert.assertTrue(viewModel.state().value is SentimentState.SentimentGenerated)
    }

    @Test fun shouldGetErrorOnAuthGetting() {
        forceToGetAuthErrorState(Throwable())
        viewModel.getSentimentFor("")
        Assert.assertTrue(viewModel.state().value is SentimentState.ErrorWhenGenerateSentiment)
    }

    private fun forceToGetAuthErrorState(error: Throwable) {
        val sentimentRepository = mock<SentimentRepository>()
        interactor.testWith(repository)
        whenever(repository.forSentiment()).thenReturn(sentimentRepository)
        whenever(sentimentRepository.getSentimentFor(any())).thenReturn(Single.create {
            it.onError(error)
        })
    }
}