package com.shrpereira.tweetsense.app.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.shrpereira.tweetsense.app.R
import com.shrpereira.tweetsense.app.common.extension.observe
import com.shrpereira.tweetsense.app.ui.base.BaseActivity
import com.shrpereira.tweetsense.app.ui.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "SplashActivity"

class SplashActivity : BaseActivity() {

	private val viewModel by viewModel<SplashViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)

		Log.d(TAG, "Activity started")
	}

	override fun onResume() {
		super.onResume()

		GlobalScope.launch(Dispatchers.Main) {
			delay(1000)
			observe(viewModel.twitterAuthLiveData, ::handleAuthentication)
			observe(viewModel.googleAuthLiveData, ::handleAuthentication)
		}
	}

	private fun handleAuthentication(state: SplashViewModel.AuthState) {
		when (state) {
			is SplashViewModel.AuthState.Success -> {
				val intent = Intent(this@SplashActivity, MainActivity::class.java)
				intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
				startActivity(intent)
			}
			is SplashViewModel.AuthState.Error -> {
				Toast.makeText(this@SplashActivity, state.message, Toast.LENGTH_LONG).show()
			}
		}
	}
}
