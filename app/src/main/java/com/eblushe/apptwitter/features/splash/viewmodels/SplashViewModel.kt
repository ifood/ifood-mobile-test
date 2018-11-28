package com.eblushe.apptwitter.features.splash.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eblushe.apptwitter.common.models.DataHolder
import com.eblushe.apptwitter.common.models.OAuthToken
import com.eblushe.apptwitter.common.providers.RxProvider
import com.eblushe.apptwitter.common.repositories.AuthRepository

class SplashViewModel(
    var appTokenLiveData: MutableLiveData<DataHolder<OAuthToken>> = MutableLiveData(),
    val authRepository: AuthRepository,
    val rxProvider: RxProvider)
    : ViewModel() {

    fun requestToken() {
        appTokenLiveData.value = DataHolder(state = DataHolder.State.LOADING)
        val disposable = authRepository.requestAuthToken()?.
            retry(3L)?.
            subscribe(::onRequestTokenSuccess, ::onRequestTokenError)
        disposable?.apply { rxProvider.addDisposable(this) }
    }

    private fun onRequestTokenSuccess(token: OAuthToken ) {
        appTokenLiveData.value = DataHolder(value = token, state = DataHolder.State.LOADED)
    }

    private fun onRequestTokenError(throwable: Throwable) {
        appTokenLiveData.value = DataHolder(error = Exception(throwable))
    }

    override fun onCleared() {
        rxProvider.clearAllDisposable()
        super.onCleared()
    }
}