package com.eblushe.apptwitter.features.splash.views

import android.os.Bundle
import androidx.lifecycle.Observer
import com.eblushe.apptwitter.R
import com.eblushe.apptwitter.common.models.DataHolder
import com.eblushe.apptwitter.common.models.OAuthToken
import com.eblushe.apptwitter.common.providers.RouterProvider
import com.eblushe.apptwitter.common.views.BaseActivity
import com.eblushe.apptwitter.features.splash.viewmodels.SplashViewModel
import kotlinx.android.synthetic.main.activity_splash.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SplashActivity : BaseActivity<SplashViewModel>() {

    override val viewModel: SplashViewModel by viewModel()
    private var  needRetryRequestToken = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onLoadUI()
        onLoadLiveData(viewModel)

        viewModel.requestToken()
    }

    override fun onResume() {
        super.onResume()

        if (needRetryRequestToken) { // TODO: refatorar, jogar logica para viewmodel FINISED
            viewModel.requestToken()
        }
    }

    override fun onLoadUI() {

    }

    override fun onLoadLiveData(viewModel: SplashViewModel) {
        viewModel.appTokenLiveData.observe(this, Observer(::observeAppToken))
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    private fun observeAppToken(holder: DataHolder<OAuthToken>) {
        needRetryRequestToken = false
        when(holder.state) {
            DataHolder.State.LOADING -> { showLoading(progressBar) }
            DataHolder.State.LOADED -> { onAppTokenLoaded(holder)}
            else -> { onAppTokenError() }
        }
    }

    private fun onAppTokenLoaded(holder: DataHolder<OAuthToken>) {
        hideLoading(progressBar)
        holder.state = DataHolder.State.FINISHED
        uiMessageTextView.text = getString(R.string.finished)
        RouterProvider.openHomeScreen(this, finish = true)
    }

    private fun onAppTokenError() {
        hideLoading(progressBar)
        needRetryRequestToken = true
        uiMessageTextView.text = getString(R.string.sorry_cant_start_application)
    }
}
