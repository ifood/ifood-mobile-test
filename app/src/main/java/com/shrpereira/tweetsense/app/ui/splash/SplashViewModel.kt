package com.shrpereira.tweetsense.app.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.shrpereira.tweetsense.data.model.DataResource
import com.shrpereira.tweetsense.domain.authentication.AuthenticationUseCase

class SplashViewModel(private val authenticationUseCase: AuthenticationUseCase) : ViewModel() {

	private var twitterAuthenticated = false
	private var googleAuthenticated = false

	val twitterAuthLiveData: LiveData<AuthState> by lazy {
		Transformations.switchMap(authenticationUseCase.authenticateTwitter()) { resource ->
			twitterAuthenticated = resource.error == null
			verifyAuthState(resource)
		}
	}

	val googleAuthLiveData: LiveData<AuthState> by lazy {
		Transformations.switchMap(authenticationUseCase.authenticateGoogle()) { resource ->
			googleAuthenticated = resource.error == null
			verifyAuthState(resource)
		}
	}

	private fun verifyAuthState(resource: DataResource<Unit>): LiveData<AuthState> {
		val liveData = MutableLiveData<AuthState>()

		when {
			twitterAuthenticated && googleAuthenticated -> liveData.value = AuthState.Success
			!resource.error?.message.isNullOrEmpty() -> liveData.value =
					AuthState.Error(resource.error?.message ?: "")
		}

		return liveData
	}

	sealed class AuthState {
		object Success : AuthState()
		data class Error(val message: String) : AuthState()
	}
}