package bloder.com.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import bloder.com.domain.api_response.auth.AUTH_ERROR
import bloder.com.domain.api_response.auth.AuthError
import bloder.com.domain.interactor.TwitterInteractor
import bloder.com.domain.repository.RepositoryFactory
import bloder.com.domain.repository.resources.AuthRepository
import bloder.com.presentation.twitter.auth.AuthState
import bloder.com.presentation.twitter.auth.AuthViewModel
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
class AuthViewModelTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val interactor = mock<TwitterInteractor>()
    private val repository = mock<RepositoryFactory>()
    private val viewModel = AuthViewModel(interactor)

    @Before fun setup() {
        interactor.test()
    }

    @Test fun completeGetTwitterAuth() {
        viewModel.getTwitterAuthToken("")
        Assert.assertTrue(viewModel.state().value is AuthState.TwitterAuthTokenFetched)
    }

    @Test fun shouldGetErrorOnAuthGetting() {
        forceToGetAuthErrorState(AuthError(AUTH_ERROR.UNKNOWN, ""))
        viewModel.getTwitterAuthToken("")
        Assert.assertTrue(viewModel.state().value is AuthState.TwitterAuthTokenFetchFailed)
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