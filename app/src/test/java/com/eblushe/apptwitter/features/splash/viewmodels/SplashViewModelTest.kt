package com.eblushe.apptwitter.features.splash.viewmodels

import androidx.lifecycle.MutableLiveData
import com.eblushe.apptwitter.common.models.DataHolder
import com.eblushe.apptwitter.common.models.OAuthToken
import com.eblushe.apptwitter.common.repositories.AuthRepository
import com.eblushe.apptwitter.common.tests.BaseUnitTest
import io.reactivex.Single

import org.junit.Before
import org.junit.Test
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext
import org.koin.standalone.getKoin
import org.mockito.BDDMockito.given
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times

class SplashViewModelTest : BaseUnitTest() {

    private lateinit var viewModel: SplashViewModel

    @Mock
    lateinit var authRepository: AuthRepository

    @Mock
    lateinit var appTokenLiveData: MutableLiveData<DataHolder<OAuthToken>>

    @Before
    fun before() {
        StandAloneContext.loadKoinModules(listOf(
            module {
                single(override = true) { authRepository }
            }
        ))

        viewModel = getKoin().get()
        viewModel.appTokenLiveData = appTokenLiveData
    }

    @Test
    fun requestToken_WhenSuccess() {
        val accessToken = "qwerty"
        val singleToken = Single.just(OAuthToken(accessToken))

        given(authRepository.requestAuthToken()).willReturn(singleToken)

        viewModel.requestToken()

        Mockito.verify(appTokenLiveData, times(2)).value = any()
    }

    @Test
    fun requestToken_WhenError() {
        val singleTokenError = Single.error<OAuthToken> { Throwable("Unauthorized") }

        given(authRepository.requestAuthToken()).willReturn(singleTokenError)

        viewModel.requestToken()

        Mockito.verify(appTokenLiveData, times(2)).value = any()
    }
}