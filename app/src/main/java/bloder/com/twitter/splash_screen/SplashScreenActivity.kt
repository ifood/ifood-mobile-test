package bloder.com.twitter.splash_screen

import android.os.Bundle
import bloder.com.presentation.AppViewModel
import bloder.com.presentation.twitter.auth.AuthState
import bloder.com.presentation.twitter.auth.AuthViewModel
import bloder.com.twitter.R
import bloder.com.twitter.StateActivity
import org.koin.android.ext.android.inject

class SplashScreenActivity : StateActivity<AuthState>() {

    private val viewModel: AuthViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
    }

    override fun handleState(state: AuthState) = when(state) {
        is AuthState.Setup -> setup()
        is AuthState.TwitterAuthTokenFetched -> onSuccessTwitterAuthTokenFetched(state.authToken)
        is AuthState.TwitterAuthTokenFetchFailed -> onErrorTwitterAuthTokenFetched(state.errorMessage)
    }

    override fun provideViewModel(): AppViewModel<AuthState> = viewModel

    private fun setup() {
        
    }

    private fun onSuccessTwitterAuthTokenFetched(token: String) {

    }

    private fun onErrorTwitterAuthTokenFetched(errorMessage: String) {

    }
}
