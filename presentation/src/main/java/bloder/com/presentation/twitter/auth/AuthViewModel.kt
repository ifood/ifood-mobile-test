package bloder.com.presentation.twitter.auth

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.OnLifecycleEvent
import android.util.Base64
import bloder.com.domain.api_response.auth.AuthError
import bloder.com.domain.interactor.TwitterInteractor
import bloder.com.presentation.AppViewModel

class AuthViewModel(private val interactor: TwitterInteractor) : AppViewModel<AuthState>() {

    private val defaultErrorMessages = "An unknown problem has occurred"

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE) fun onCreate() {
        dispatch(AuthState.Setup)
    }

    fun getTwitterAuthToken(consumerKey: String, consumerSecret: String) {
        val auth = "Basic ${ Base64.encodeToString("$consumerKey:$consumerSecret".toByteArray(), Base64.NO_WRAP) }"
        interactor.getTwitterAuthToken(auth).subscribe {
            onSuccess { auth ->
                dispatch(AuthState.TwitterAuthTokenFetched(auth.accessToken))
            }
            onError { error ->
                when (error) {
                    is AuthError -> dispatch(AuthState.TwitterAuthTokenFetchFailed(error.errorMessage))
                    else -> dispatch(AuthState.TwitterAuthTokenFetchFailed(defaultErrorMessages))
                }
            }
        }
    }
}