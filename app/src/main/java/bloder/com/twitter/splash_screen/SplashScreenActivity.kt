package bloder.com.twitter.splash_screen

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import bloder.com.presentation.AppViewModel
import bloder.com.presentation.twitter.auth.AuthState
import bloder.com.presentation.twitter.auth.AuthViewModel
import bloder.com.twitter.BuildConfig
import bloder.com.twitter.R
import bloder.com.twitter.StateActivity
import bloder.com.twitter.preferences.AuthTokenPreferences
import bloder.com.twitter.search_user.SearchUserActivity
import org.koin.android.ext.android.inject

class SplashScreenActivity : StateActivity<AuthState>() {

    private val viewModel: AuthViewModel by inject()
    private val authPreferences: AuthTokenPreferences by inject()

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

    private fun setup() = if (authPreferences.getAuthToken(this).isNotEmpty()) {
        goToSearchActivity()
    } else { fetchForTwitterAuthToken() }

    private fun fetchForTwitterAuthToken() = viewModel.getTwitterAuthToken(BuildConfig.CONSUMER_KEY, BuildConfig.CONSUMER_SECRET)

    private fun onSuccessTwitterAuthTokenFetched(token: String) {
        authPreferences.persistTwitterAuthToken(this, token)
        goToSearchActivity()
    }

    private fun onErrorTwitterAuthTokenFetched(errorMessage: String) {
        AlertDialog.Builder(this)
                .setTitle(getString(R.string.ops_error))
                .setMessage(errorMessage)
                .setCancelable(false)
                .setPositiveButton(getString(R.string.try_again)) { dialog, _ ->
                    dialog.dismiss()
                    setup()
                }.show()
    }

    private fun goToSearchActivity() {
        startActivity(Intent(this, SearchUserActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
        )
        finish()
    }
}
