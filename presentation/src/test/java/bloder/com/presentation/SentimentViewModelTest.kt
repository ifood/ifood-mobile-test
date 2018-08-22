package bloder.com.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import bloder.com.domain.interactor.SentimentInteractor
import bloder.com.domain.models.sentiment.SENTIMENT
import bloder.com.domain.models.sentiment.Sentiment
import bloder.com.domain.repository.RepositoryFactory
import bloder.com.domain.repository.resources.SentimentRepository
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

    @Test fun shouldGetHappySentiment() {
        forceToGetSentiment(0.8)
        viewModel.getSentimentFor("test")
        Assert.assertTrue(
                viewModel.state().value is SentimentState.SentimentGenerated
                        && (viewModel.state().value as SentimentState.SentimentGenerated).sentiment == SENTIMENT.HAPPY
        )
    }

    @Test fun shouldGetNeutralSentiment() {
        forceToGetSentiment(0.2)
        viewModel.getSentimentFor("test")
        Assert.assertTrue(
                viewModel.state().value is SentimentState.SentimentGenerated
                        && (viewModel.state().value as SentimentState.SentimentGenerated).sentiment == SENTIMENT.NEUTRAL
        )
    }

    @Test fun shouldGetSadSentiment() {
        forceToGetSentiment(-0.6)
        viewModel.getSentimentFor("test")
        Assert.assertTrue(
                viewModel.state().value is SentimentState.SentimentGenerated
                        && (viewModel.state().value as SentimentState.SentimentGenerated).sentiment == SENTIMENT.SAD
        )
    }

    private fun forceToGetAuthErrorState(error: Throwable) {
        val sentimentRepository = mock<SentimentRepository>()
        interactor.testWith(repository)
        whenever(repository.forSentiment()).thenReturn(sentimentRepository)
        whenever(sentimentRepository.getSentimentFor(any())).thenReturn(Single.create {
            it.onError(error)
        })
    }

    private fun forceToGetSentiment(sentiment: Double) {
        val sentimentRepository = mock<SentimentRepository>()
        interactor.testWith(repository)
        whenever(repository.forSentiment()).thenReturn(sentimentRepository)
        whenever(sentimentRepository.getSentimentFor(any())).thenReturn(Single.create {
            it.onSuccess(Sentiment(sentiment))
        })
    }
}